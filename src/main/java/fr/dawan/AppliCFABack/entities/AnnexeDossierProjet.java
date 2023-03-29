package fr.dawan.AppliCFABack.entities;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nicolas
 *
 */
@Entity
public class AnnexeDossierProjet extends BaseEntity implements Serializable {

    @Column(nullable = false)    
    private String pieceJointe;

    @ManyToOne
    private DossierProjet dossierProjet;

    
    public String getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    public DossierProjet getDossierProjet() {
        return dossierProjet;
    }

    public void setDossierProjet(DossierProjet dossierProjet) {
        this.dossierProjet = dossierProjet;
    }
    
    

   
}
