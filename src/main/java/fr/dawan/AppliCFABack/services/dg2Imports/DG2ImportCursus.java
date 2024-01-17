package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.CursusDG2Dto;
import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.CursusRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DG2ImportCursus extends DG2ImportTools {

    private static final Logger logger = Logger.getGlobal();

    @Autowired
    CursusRepository cursusRepo;

    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();

    /**
     * Va récupérer tous les cursus de DG2
     *
     * @param email Email l'utilisateur dg2
     * @param password   Mot de passe de l'utlisateur dg2
     * @throws URISyntaxException
     *
     * @exception Exception retourne une exception,
     * si erreur dans la récupération des cursus
     */

    //import des cursus DG2
    public void fetchDG2Cursus(String email, String password) throws FetchDG2Exception, URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CursusDG2Dto> fResJson = new ArrayList<>();

        //url dg2 qui concerne la recupération des cursus
        URI url = new URI( baseUrl + "pro-titles");

        //recupérartion des headers / email / password dg2
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", email + ":" + password);

        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> repWs = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            if(repWs.getStatusCode() != HttpStatus.OK) {
                throw new FetchDG2Exception("Status Code in the webservice WDG2 not correct");

            }
            String json = repWs.getBody();

            try {
                //recuperation des values en json et lecture
                fResJson = objectMapper.readValue(json, new TypeReference<List<CursusDG2Dto>>() {
                });
            } catch (Exception e) {
                logger.log(Level.WARNING, "failed json", e);
            }
            for (CursusDG2Dto cDG2 : fResJson) {
                Cursus cursusImport = mapper.cursusDG2DtoToCursus(cDG2);
                Optional<Cursus> optCursus = cursusRepo.findByIdDg2(cursusImport.getIdDg2());
                if (optCursus.isPresent()) {
                    if (optCursus.get().equals(cursusImport))
                        continue;
                    else if (!optCursus.get().equals(cursusImport)) {
                        //cursusImport.setTitre(optCursus.get().getTitre());
                        cursusImport.setVersion(optCursus.get().getVersion());
                        cursusImport.setId(optCursus.get().getId());
                    }
                    try {
                        cursusRepo.saveAndFlush(cursusImport);
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Failed save dg2", e);
                    }
                } else {
                    try {
                        cursusRepo.saveAndFlush(cursusImport);
                    } catch (Exception e) {

                        logger.log(Level.SEVERE, "Failed save dg2", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed fetch dg2", e);

            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }

    }
}
