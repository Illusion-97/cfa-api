package fr.dawan.AppliCFABack.services.dg2Imports;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.AppliCFABack.tools.FetchDG2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class DG2Imports extends DG2ImportTools  {


    DG2ImportCenters dg2ImportCenters;
    DG2ImportCursus dg2ImportCursus;

    public DG2Imports(
            @Autowired DG2ImportCenters dg2ImportCenters,
            @Autowired DG2ImportCursus dg2ImportCursus
    ) {
        this.dg2ImportCenters = dg2ImportCenters;
        this.dg2ImportCursus = dg2ImportCursus;
    }


    public void fetchCenters(String email, String password) throws FetchDG2Exception, URISyntaxException, JsonProcessingException {
        this.dg2ImportCenters.fetchAllDG2CentreFormation(email, password);
    }

    public void fetchCursus(String email, String password) throws FetchDG2Exception, URISyntaxException {
        this.dg2ImportCursus.fetchDG2Cursus(email, password);
    }

    public void fetchUsers(String email, String password) throws FetchDG2Exception, URISyntaxException {
        //this.dg2ImportUsers.fetchDG2Users(email, password);
    }

    public void fetchFormations(String email, String password) throws FetchDG2Exception, URISyntaxException {
        //this.dg2ImportFormations.fetchDG2Formations(email, password);
    }
}
