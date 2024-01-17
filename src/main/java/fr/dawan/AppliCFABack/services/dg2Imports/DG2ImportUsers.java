package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.EmployeeDG2Dto;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class DG2ImportUsers extends DG2ImportTools {

    private final DtoMapper mapper;

    private final CentreFormationRepository centreFormationRepository;

    private final UtilisateurRepository utilisateurRepository;

    private static final Logger logger = LoggerFactory.getLogger(DG2ImportUsers.class);


    public DG2ImportUsers(
            @Autowired DtoMapper mapper,
            @Autowired CentreFormationRepository centreFormationRepository,
            @Autowired UtilisateurRepository utilisateurRepository
    ) {
        this.mapper = mapper;
        this.centreFormationRepository = centreFormationRepository;
        this.utilisateurRepository =utilisateurRepository;


    }

    /**
     * Enregistre en base de données le employees récupéré de DG2
     *
     * @param email , password
     * @return void
     */
    public void fetchAllDG2Employees(String email, String password)
            throws FetchDG2Exception, URISyntaxException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<EmployeeDG2Dto> cResJson;

        // url dg2 qui concerne la recupération des locations
        URI url = new URI(baseUrl + "employees");
        ResponseEntity<String> repWs = this.executeRequestOnDG2API(email, password, url);

        if (repWs.getStatusCode() == HttpStatus.OK) {
            String json = repWs.getBody();
            // recuperation des values en json et lecture
            cResJson = objectMapper.readValue(json, new TypeReference<List<EmployeeDG2Dto>>() {
            });
            // boucle pour récupérer toute la liste
            for (EmployeeDG2Dto eDG2 : cResJson) {
                Utilisateur utilisateurImport = mapper.employeeDg2ToUtilisateur(eDG2);
                Optional<CentreFormation> centreFormationOpt = centreFormationRepository
                        .findByIdDg2(eDG2.getLocationId());
                Optional<Utilisateur> optUtlisateur = utilisateurRepository.findByIdDg2(utilisateurImport.getIdDg2());
                if (centreFormationOpt.isPresent()) {
                    utilisateurImport.setCentreFormation(centreFormationOpt.get());
                } else {
                    logger.error("SaveAndFlush failed", "Centre de formation Introuvable");
                    throw new FetchDG2Exception("Centre de formation Introuvable");
                }

                if (optUtlisateur.isPresent()) {

                    if (optUtlisateur.get().equals(utilisateurImport)
                            && optUtlisateur.get().getCentreFormation().getId() == centreFormationOpt.get().getId()) {
                        continue;
                    } else {
                        if (utilisateurImport != null) {
                            utilisateurImport.setPassword(optUtlisateur.get().getPassword());
                            utilisateurImport.setId(optUtlisateur.get().getId());
                            utilisateurImport.setVersion(optUtlisateur.get().getVersion());
                            utilisateurImport.setActive(true);

                        }
                    }
                    try {
                        utilisateurRepository.saveAndFlush(utilisateurImport);

                    } catch (Exception e) {
                        logger.error("SaveAndFlush failed", e);

                    }
                } else {

                    try {

                        utilisateurImport.setPassword(null);
                        utilisateurImport.setActive(true);
                        utilisateurRepository.saveAndFlush(utilisateurImport);

                    } catch (Exception e) {
                        logger.error("SaveAndFlush failed", e);

                    }
                }
            }
        } else {
            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }
    }

}
