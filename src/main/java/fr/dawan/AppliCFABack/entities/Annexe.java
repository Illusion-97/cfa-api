package fr.dawan.AppliCFABack.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Annexe extends BaseEntity implements Serializable {

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String pieceJointe;

    @ManyToOne
    private DossierProfessionnel dossierProfessionnel;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
    }

    public DossierProfessionnel getDossierProfessionnel() {
        return dossierProfessionnel;
    }

    public void setDossierProfessionnel(DossierProfessionnel dossierProfessionnel) {
        this.dossierProfessionnel = dossierProfessionnel;
    }
}
