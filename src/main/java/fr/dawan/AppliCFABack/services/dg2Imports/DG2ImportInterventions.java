package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionOrInterventionDG2Dto;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.*;
import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.services.InterventionServiceImpl;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DG2ImportInterventions extends DG2ImportTools {

    private final PromotionRepository promoRepository;
    private final InterventionRepository interventionRepository;
    private final FormateurRepository formateurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final FormationRepository formationRepository;
    private final UtilisateurRoleRepository utilisateurRoleRepository;
    private static final Logger logger = LoggerFactory.getLogger(InterventionServiceImpl.class);

    public DG2ImportInterventions(
            @Autowired PromotionRepository promoRepository,
            @Autowired InterventionRepository interventionRepository,
            @Autowired FormateurRepository formateurRepository,
            @Autowired UtilisateurRepository utilisateurRepository,
            @Autowired FormationRepository formationRepository,
            @Autowired UtilisateurRoleRepository utilisateurRoleRepository
    ) {
        this.promoRepository = promoRepository;
        this.interventionRepository = interventionRepository;
        this.formateurRepository = formateurRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.formationRepository = formationRepository;
        this.utilisateurRoleRepository = utilisateurRoleRepository;
    }

    /**
     * Va permettre l'import des intervention de Dg2* @param email    Email l'utilsateur dg2
     * @param password Mot de passe de l'utlisateur dg2
     * @return List Liste des interventions
     * @throws Exception
     */
    @Async("myTaskExecutor")
    public void fetchDGInterventions(String email, String password) throws Exception {
        List<Promotion> promoLst = new ArrayList<>();
        promoLst = promoRepository.findAll();
        //int result = 0;
        for (Promotion p : promoLst) {
            fetchDGInterventions(email, password, p.getIdDg2());

        }

        //return result;
    }

    @Async("myTaskExecutor")
    public int fetchDGInterventions(String email, String password, long idPrmotionDg2) throws Exception {
        List<Intervention> interventions = new ArrayList<>();
        interventions.addAll(getInterventionDG2ByIdPromotionDG2(email, password, idPrmotionDg2));
        for (Intervention i : interventions) {
            try {
                interventionRepository.saveAndFlush(i);
                // saveOrUpdate(DtoTools.convert(i, InterventionDto.class));
            } catch (Exception e) {
                logger.warn("save and flush intervention dg2 failed", e);
            }
        }
        return interventions.size();
    }


    /**
     * Va permettre l'import des intervention de Dg2
     *
     * @author Feres BG
     * @param email    Email l'utilsateur dg2
     * @param password Mot de passe de l'utlisateur dg2
     * @return List Liste des interventions
     * @throws Exception
     */

    @Async("myTaskExecutor")
    public List<Intervention> getInterventionDG2ByIdPromotionDG2(String email, String password, long idPrmotionDg2)
            throws Exception {
        Optional<Promotion> promotionOpt = promoRepository.findByIdDg2(idPrmotionDg2);
        logger.info(">>>>>>>promo>>>>>" + promotionOpt.get().getIdDg2());
        logger.info("FetchDg2Intervention >>> START");
        if (!promotionOpt.isPresent()) {
            logger.error("FetchDg2Intervention>>>>>>>>ERROR failed Pas de promo");
            throw new FetchDG2Exception("Promotion introuvable veuiller mettre à jour les promotions");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<PromotionOrInterventionDG2Dto> fetchResJson = new ArrayList<>();
        List<Intervention> result = new ArrayList<>();

        URI url = new URI(baseUrl + "sessions/" + idPrmotionDg2 + "/children");
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", email + ":" + password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        logger.info("FetchDg2Intervention >>> START /children");
        if (rep.getStatusCode() == HttpStatus.OK) {
            String json = rep.getBody();

            try {
                fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionOrInterventionDG2Dto>>() {
                });
            } catch (Exception e) {
                logger.warn("json intervention dg2 failed", e);
            }
            DtoTools dtoTools = new DtoTools();
            if (!fetchResJson.isEmpty()) {

//				int countFormateurs = findFormateur(fetchResJson);
//				logger.info("Nombre de formateurs ajoutés : " + countFormateurs);

                findFormateur(fetchResJson);
                logger.info("FetchDg2Intervention >>> START /for");
                for (PromotionOrInterventionDG2Dto iDtoDG2 : fetchResJson) {

                    Intervention interventionImported = dtoTools.promotionOrInterventionDG2DtoToIntervention(iDtoDG2);

                    Optional<Intervention> intervInDb = interventionRepository.findByIdDg2(iDtoDG2.getId());

                    if (intervInDb.isPresent()) {
                        if (interventionImported.equals(intervInDb.get())) {
                            continue;
                        }
                        intervInDb.get().setDateDebut(interventionImported.getDateDebut());
                        intervInDb.get().setDateFin(interventionImported.getDateFin());
                        Optional<Promotion> promotion = promoRepository.findByIdDg2(idPrmotionDg2);
                        if (promotion.isPresent() && interventionImported.getPromotions() != null) {

                            if (!interventionImported.getPromotions().contains(promotion.get())) {
                                // Ajout de la promotion à la liste des promotions de l'intervention importée
                                interventionImported.getPromotions().add(promotion.get());
                            }
                        } else {
                            logger.warn("Promotion not found with idDg2 : " + idPrmotionDg2);
                            continue;
                        }
                        // Récupération de la liste des promotions de l'intervention en base
                        List<Promotion> promotionsInDb = intervInDb.get().getPromotions();
                        if (promotionsInDb != null && !promotionsInDb.isEmpty()) {
                            // Ajout de la liste des promotions de l'intervention en base à la liste des
                            // promotions de l'intervention importée
                            interventionImported.getPromotions().addAll(promotionsInDb);
                        }
                    } else {
                        // Récupération de la promotion correspondante à l'ID en paramètre
                        Optional<Promotion> promotion = promoRepository.findByIdDg2(idPrmotionDg2);
                        if (interventionImported != null && promotion != null && promotion.isPresent()) {
                            if (interventionImported.getPromotions() == null) {
                                interventionImported.setPromotions(new ArrayList<>());
                            }
                            interventionImported.getPromotions().add(promotion.get());
                        } else {
                            logger.warn("Promotion not found with idDg2 : " + idPrmotionDg2);
                            continue;
                        }
                    }

                    Optional<Formation> formation = formationRepository.findByIdDg2(iDtoDG2.getCourseId());
                    if (!formation.isPresent()) {
                        logger.warn("Formation not found with idDg2 : " + iDtoDG2.getCourseId());
                        continue;
                    }
                    interventionImported.setFormation(formation.get());

                    Optional<Formateur> formateur = formateurRepository.findByIdDg2(iDtoDG2.getTrainerPersonId());
                    logger.info("ID DG2 du formateur  : " + formateur);

                    if (!formateur.isPresent()) {
                        logger.warn("Formateur not found with idDg2 : " + iDtoDG2.getTrainerPersonId());
                        continue;
                    }

                    interventionImported.setFormateur(formateur.get());

                    interventionRepository.saveAndFlush(interventionImported);

                    // utilisateurRepository.saveAndFlush(formateur.get().getUtilisateur());

                    logger.info("interventionImported >>> ");
                    result.add(interventionImported);
                }

            }
            logger.info("FetchDg2Intervention>>>>>>END");
        } else {
            logger.error("FetchDg2Intervention>>>>>>>>ERROR End failed");
            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }
        return result;
    }



    public int findFormateur(List<PromotionOrInterventionDG2Dto> iDtoDG2) {

        int count = 0;

        for (PromotionOrInterventionDG2Dto i : iDtoDG2) {

            Optional<Utilisateur> userInDb = utilisateurRepository.findByIdDg2(i.getTrainerPersonId());
            logger.info("ID DG2 user  : " + userInDb);

            if (!userInDb.isPresent()) {
                continue;
            }

            Optional<Formateur> formateurInDB = formateurRepository.findByUtilisateurId(userInDb.get().getId());
            if (formateurInDB.isPresent()) {
                continue;

            }
            Formateur formateur = new Formateur();

            UtilisateurRole formateurRole = utilisateurRoleRepository.findByIntituleContaining("FORMATEUR");

            List<Utilisateur> utilisateurs = new ArrayList<>();

            utilisateurs.add(userInDb.get());

            if (formateurRole.getUtilisateurs() != null) {
                utilisateurs.addAll(formateurRole.getUtilisateurs());
            }

            formateurRole.setUtilisateurs(utilisateurs);
            if (userInDb.get().getRoles() != null) {
                if (!userInDb.get().getRoles().contains(formateurRole)) {
                    userInDb.get().getRoles().add(formateurRole);
                }
            } else {
                List<UtilisateurRole> roles = new ArrayList<>();
                roles.add(formateurRole);
                userInDb.get().setRoles(roles);
            }
            formateur.setUtilisateur(userInDb.get());
            formateur = formateurRepository.saveAndFlush(formateur);
            userInDb.get().setFormateur(formateur);
            utilisateurRepository.saveAndFlush(userInDb.get());

            count++;

        }
        return count;

    }
}
