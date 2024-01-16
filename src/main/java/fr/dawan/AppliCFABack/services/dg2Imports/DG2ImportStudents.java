package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.EtudiantUtilisateurDG2Dto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DG2ImportStudents extends DG2ImportTools {

    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();

    private final PromotionRepository promotionRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final EtudiantRepository etudiantRepository;

    private final AdresseRepository adresseRepository;

    private final LivretEvaluationRepository livretEvaluationRepository;

    private static final Logger logger = LoggerFactory.getLogger(DG2ImportStudents.class);

    private final BlocEvaluationRepository blocEvaluationRepository;



    public DG2ImportStudents(
            @Autowired PromotionRepository promotionRepository,
            @Autowired UtilisateurRepository utilisateurRepository,
            @Autowired EtudiantRepository etudiantRepository,
            @Autowired AdresseRepository adresseRepository,
            @Autowired LivretEvaluationRepository livretEvaluationRepository,
            @Autowired BlocEvaluationRepository blocEvaluationRepository
    ) {
        this.promotionRepository = promotionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.etudiantRepository = etudiantRepository;
        this.adresseRepository = adresseRepository;
        this.livretEvaluationRepository = livretEvaluationRepository;
        this.blocEvaluationRepository = blocEvaluationRepository;
    }

    @Async("myTaskExecutor")
    public void fetchAllEtudiantDG2(String email, String password) throws Exception {
        List<Promotion> promotions = promotionRepository.findAll();

        for (Promotion promotion : promotions) {

            fetchAllEtudiantDG2ByIdPromotion(email, password, promotion.getIdDg2());
        }

    }

    @Async("myTaskExecutor")
    public void fetchAllEtudiantDG2ByIdPromotion(String email, String password, long idPromotionDg2)
            throws FetchDG2Exception, JsonProcessingException, URISyntaxException {
        List<Etudiant> saved = new ArrayList<>();
        Optional<Promotion> optionnalPromotion = promotionRepository.findByIdDg2(idPromotionDg2);

        if (!optionnalPromotion.map(promotion -> {
            logger.info(">>>>>>>promo>>>>" + promotion.getIdDg2());
            logger.info("FetchDg2Etudiant >>> START");
            ObjectMapper objectMapper = new ObjectMapper();
            List<EtudiantUtilisateurDG2Dto> cResJson = new ArrayList<>();

            String url =  baseUrl + "sessions/" + idPromotionDg2 + "/registrations";

            HttpHeaders headers = new HttpHeaders();
            headers.add("x-auth-token", email + ":" + password);

            HttpEntity<String> httpEntity = new HttpEntity<>(headers);

            ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            logger.info("FetchDg2Etudiant >>> START /registration");

            if (repWs.getStatusCode() == HttpStatus.OK) {
                logger.info("FetchDg2Etudiant >>> START /registration OK");
                String json = repWs.getBody();
                // importUserFromJson(json, promotion);

                try {
                    cResJson = objectMapper.readValue(json, new TypeReference<List<EtudiantUtilisateurDG2Dto>>() {
                    });
                } catch (Exception ignored) {
                }
                for (EtudiantUtilisateurDG2Dto eDG2 : cResJson) {
                    logger.info("FetchDg2Etudiant >>> START /for" + eDG2.getPersonId());

                    Optional<Utilisateur> utiLisateurOptional = utilisateurRepository
                            .findDistinctByIdDg2(eDG2.getPersonId());
                    Etudiant etudiant = null;
                    Utilisateur utilisateur = null;
                    if (utiLisateurOptional.isPresent()) { //utilisateur existant, on cherche l'étudiant
                        etudiant = etudiantRepository.findByUtilisateurId(utiLisateurOptional.get().getId());
                        utilisateur = utiLisateurOptional.get();
                    } else { //utilisateur non existant => on le crée
                        utilisateur = mapper.etudiantUtilisateurDG2DtoToUtilisateur(eDG2);
                        Adresse userAdresse = utilisateur.getAdresse();
                        Adresse adresseDg2 = mapper.etudiantUtilisateurDG2DtoToAdresse(eDG2);
                        if (userAdresse != null) {
                            adresseDg2.setId(utilisateur.getAdresse().getId());
                            adresseDg2.setVersion(utilisateur.getAdresse().getVersion());
                            adresseRepository.saveAndFlush(adresseDg2);
                        } else {
                            adresseDg2 = adresseRepository.saveAndFlush(adresseDg2);
                            utilisateur.setAdresse(adresseDg2);
                        }
                    }

                    //modif du rôle
                    List<UtilisateurRole> roles = utilisateur.getRoles() == null ? new ArrayList<>()
                            : utilisateur.getRoles();
                    if (roles.stream().noneMatch(r -> r.getId() == 1L)) {
                        UtilisateurRole r = new UtilisateurRole();
                        r.setId(1L);
                        roles.add(r);
                    }
                    utilisateur.setRoles(roles);
                    utilisateur.setActive(true);
                    utilisateur.setPassword(null);
                    utilisateurRepository.saveAndFlush(utilisateur);

                    final Utilisateur util = utilisateur;
                    if (etudiant == null) {
                        etudiant = saved.stream().filter(e -> e.getUtilisateur().getIdDg2() == util.getIdDg2())
                                .findFirst().orElse(null);
                        if (etudiant == null) {
                            etudiant = new Etudiant();
                            etudiant.setUtilisateur(utilisateur);
                            etudiant = etudiantRepository.saveAndFlush(etudiant);
                            saved.add(etudiant);
                        }
                    } else {
                        saved.add(etudiant);
                    }

                    long etudiantId = etudiant.getId();
                    if (promotion.getEtudiants().stream().anyMatch(e -> e.getId() == etudiantId))
                        continue;
                    else {
                        promotion.getEtudiants().add(etudiant);
                        promotionRepository.save(promotion);
                    }
                    Optional<LivretEvaluation> evaOptional = livretEvaluationRepository
                            .findByEtudiantIdAndTitreProfessionnelId(etudiant.getId(), promotion.getCursus().getId());
                    if (!evaOptional.isPresent()) {
                        LivretEvaluation livret = new LivretEvaluation();
                        livret.setEtudiant(etudiant);
                        livret.setTitreProfessionnel(promotion.getCursus());
                        livret.setOrganismeFormation(promotion.getCentreFormation());
                        livret.setEtat(LivretEvaluation.EtatLivertEval.ENATTENTEDEVALIDATION);
                        livret = livretEvaluationRepository.saveAndFlush(livret);
                        final LivretEvaluation fLivret = livret;
                        promotion.getCursus().getActiviteTypes().forEach(at -> {
                            BlocEvaluation blocEvaluation = new BlocEvaluation();
                            blocEvaluation.setLivretEvaluation(fLivret);
                            blocEvaluation.setActiviteType(at);
                            blocEvaluationRepository.saveAndFlush(blocEvaluation);
                        });
                    }

                }
                logger.info("FetchDg2Etudiant>>>>>>END");
                return true;
            } else {
                logger.error("FetchDg2Etudiant>>>>>>>>ERROR End failed");
                return false;
            }
        }).orElseThrow(() -> new FetchDG2Exception("Promotion Introuvable"))) {
            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }
    }
}
