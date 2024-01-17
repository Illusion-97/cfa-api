package fr.dawan.AppliCFABack.services.dg2Imports;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.PromotionOrInterventionDG2Dto;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.repositories.PromotionRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DG2ImportPromotions extends DG2ImportTools {

    private final CursusRepository cursusRepository;

    private final CentreFormationRepository centreFormationRepository;

    private final PromotionRepository promotionRepository;

    private static final Logger logger = Logger.getGlobal();


    public DG2ImportPromotions(
            @Autowired CursusRepository cursusRepository,
            @Autowired CentreFormationRepository centreFormationRepository,
            @Autowired PromotionRepository promotionRepository
    ) {
        this.cursusRepository = cursusRepository;
        this.centreFormationRepository = centreFormationRepository;
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getPromotionDG2ByIdCursusDG2(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        List<PromotionOrInterventionDG2Dto> fetchResJson = new ArrayList<>();
        List<Promotion> result = new ArrayList<>();

        URI url = new URI(baseUrl + "trainings/" +idCursusDg2+ "/sessions");
        ResponseEntity<String> rep = this.executeRequestOnDG2API(email, password, url);

        if(rep.getStatusCode()  == HttpStatus.OK) {
            String json = rep.getBody();

            try {
                fetchResJson = objectMapper.readValue(json, new TypeReference<List<PromotionOrInterventionDG2Dto>>() {} );
            } catch (Exception e) {
                logger.log(Level.SEVERE,"objectMapper failed", e);
            }


            for(PromotionOrInterventionDG2Dto pDtoDG2 : fetchResJson) {
                Optional<Promotion> promoDb = promotionRepository.findByIdDg2(pDtoDG2.getId());

                DtoTools dtoTools = new DtoTools();
                Promotion promotionDG2 = new Promotion();
                try {
                    promotionDG2 = dtoTools.promotionOrInterventionDG2DtoToPromotion(pDtoDG2);

                } catch (Exception e) {
                    logger.log(Level.SEVERE,"dtoTools failed", e);
                }
//				Optional<CentreFormation> cfOpt = centreFormationRepository.findByIdDg2(pDtoDG2.getLocationId());
//				// valeur null centre de formation Id cause le problem
//				System.out.println(">>>>>>>>>" + pDtoDG2.getId() + ":" +pDtoDG2.getLocationId());
//				if(cfOpt.isPresent()) {
//					promotionDG2.setCentreFormation(cfOpt.get());
//				}
                Optional<Cursus> cursusOpt = cursusRepository.findByIdDg2(pDtoDG2.getCourseId());
                if(cursusOpt.isPresent()) {
                    promotionDG2.setCursus(cursusOpt.get());
                }
                Optional<CentreFormation> centreDeFormationOptional = centreFormationRepository.findByIdDg2(pDtoDG2.getLocationId());
                if (centreDeFormationOptional.isPresent()) {
                    promotionDG2.setCentreFormation(centreDeFormationOptional.get());
                }
                promotionDG2.setType(pDtoDG2.getType());
                promotionDG2.setNbParticipants(pDtoDG2.getNbParticipants());
                //comparer voir sil existe en BDD
                if(!promoDb.isPresent()) {
                    result.add(promotionDG2);
                    //si existe en BDD -> comparer tous les champs et si différents -> faire update
                } else {
                    if(!promoDb.get().equals(promotionDG2)) {
                        promotionDG2.setId(promoDb.get().getId());
                        promotionDG2.setVersion(promoDb.get().getVersion());
                        promotionDG2.setType(pDtoDG2.getType());
                        promotionDG2.setNbParticipants(pDtoDG2.getNbParticipants());
                        result.add(promotionDG2);
                    }
                }
            }
            return result;
        } else {
            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }


    }public int fetchDGPromotions(String email, String password) throws FetchDG2Exception, URISyntaxException {
        List<Cursus> cursus = new ArrayList<>();
        List<Promotion> promoLst = new ArrayList<>();
        cursus = cursusRepository.findAll();
        for(Cursus c: cursus) {
            List<Promotion> promoDg2 = new ArrayList<>();
            promoDg2 = getPromotionDG2ByIdCursusDG2(email, password, c.getIdDg2());
            if(!promoDg2.isEmpty() ) {
                promoLst.addAll(promoDg2);
            }
            else {
                logger.info(c.getId()+"Liste non vide");
            }
        }
        for(Promotion p : promoLst) {
            try {
                Promotion pps =	promotionRepository.saveAndFlush(p);
                System.out.println("pps.getId() = " + pps.getId());
            } catch (Exception e) {
                logger.log(Level.SEVERE,"SaveAndFlush failed", e);
            }
        }
        return promoLst.size();
    }
    public int fetchDGPromotions(String email, String password, long idCursusDg2) throws FetchDG2Exception, URISyntaxException {
        List<Promotion> promoLst = getPromotionDG2ByIdCursusDG2(email, password, idCursusDg2);

        for(Promotion p : promoLst) {
            try {
                promotionRepository.saveAndFlush(p);
            } catch (Exception e) {
                logger.log(Level.SEVERE,"SaveAndFlush failed", e);
            }
        }
        return promoLst.size();
    }
}
