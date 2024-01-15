package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.dto.CentreFormationDG2Dto;
import fr.dawan.AppliCFABack.entities.Adresse;
import fr.dawan.AppliCFABack.entities.CentreFormation;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.AdresseRepository;
import fr.dawan.AppliCFABack.repositories.CentreFormationRepository;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DG2ImportCenters extends DG2ImportTools {


    private final AdresseRepository adresseRepository;

    private final CentreFormationRepository centreFormationRepository;
    private static final Logger logger = Logger.getGlobal();

    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();


    public DG2ImportCenters(
            @Autowired AdresseRepository adresseRepository,
            @Autowired CentreFormationRepository centreFormationRepository
    ) {
        super();
        this.adresseRepository = adresseRepository;
        this.centreFormationRepository = centreFormationRepository;
    }


    /**
     * Va récupérer tous les centres de formation de DG2
     *
     * @param email Email l'utilsateur dg2
     * @param password   Mot de passe de l'utlisateur dg2
     * @throws URISyntaxException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     *
     * @exception Exception retourne une exception si erreur dans la récupération des centres
     */

    public void fetchAllDG2CentreFormation(String email, String password) throws FetchDG2Exception, URISyntaxException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CentreFormationDG2Dto> cResJson;

        //url dg2 qui concerne la recupération des locations
        URI url = new URI(this.baseUrl + "locations");

        //recupérartion des headers / email / password dg2
        ResponseEntity<String> repWs = this.executeRequestOnDG2API(email, password, url);

        if (repWs.getStatusCode() != HttpStatus.OK) {
            throw new FetchDG2Exception("ResponseEntity from the webservice WDG2 not correct");
        }

        String json = repWs.getBody();
        //recuperation des values en json et lecture

        cResJson = objectMapper.readValue(json, new TypeReference<List<CentreFormationDG2Dto>>() {
        });
        cResJson.forEach(this::handleCenterFormation);
    }

    private void handleCenterFormation(CentreFormationDG2Dto cDG2) {
        CentreFormation centreImport = mapper.centreFormationDG2DtoToCentreFormation(cDG2);
        Optional<CentreFormation> optCentre = centreFormationRepository.findByIdDg2(centreImport.getIdDg2());
        //Vérification et ajout de l'adresse

        if ( cDG2.getAddress() != null && !cDG2.getAddress().trim().isEmpty()) {
            this.handleAddress(cDG2, centreImport, optCentre);
        }

        if (optCentre.isPresent()) {
            if (optCentre.get().equals(centreImport))
                return;
            else if (!optCentre.get().equals(centreImport)) {
                if (centreImport != null) {
                    centreImport.setId(optCentre.get().getId());
                    centreImport.setVersion(optCentre.get().getVersion());
                }
            }
            centreFormationRepository.saveAndFlush(centreImport);
        } else {
            try {
                centreFormationRepository.saveAndFlush(centreImport);

            } catch (Exception e) {
                logger.log(Level.SEVERE,"SaveAndFlush failed", e);
            }
        }

    }


    private void handleAddress(CentreFormationDG2Dto cDG2, CentreFormation centreImport, Optional<CentreFormation> optCentre) {
        Adresse adresse = new Adresse();
        adresse.setLibelle(cDG2.getAddress());
        adresse.setCodePostal(cDG2.getZipCode());
        adresse.setVille(cDG2.getCity());
        adresse.setCountryCode(cDG2.getCountry());

        if(!optCentre.isPresent()) {
            centreImport.setAdresse(adresse);
        } else {
            this.handleAddressCreation(optCentre.get(), adresse);
        }
    }

    private void handleAddressCreation(CentreFormation optCentre, Adresse adresse) {
        if (!adresse.equals(optCentre.getAdresse())) {
            if (optCentre.getAdresse() != null) {
                adresse.setId(optCentre.getAdresse().getId());
                adresse.setVersion(optCentre.getAdresse().getVersion());
                adresseRepository.saveAndFlush(adresse);
            }
        }
    }
}
