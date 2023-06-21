package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;



import java.io.File;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Annexe extends BaseEntity implements Serializable {

	@Column(nullable = true)
	private String libelleAnnexe;

    @Column(nullable = true)
    private File pieceJointe;

    @ManyToOne
    private DossierProfessionnel dossierProfessionnel;

	public String getLibelleAnnexe() {
		return libelleAnnexe;
	}

	public void setLibelleAnnexe(String libelleAnnexe) {
		this.libelleAnnexe = libelleAnnexe;
	}


	public File getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(File pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	public DossierProfessionnel getDossierProfessionnel() {
        return dossierProfessionnel;
    }

    public void setDossierProfessionnel(DossierProfessionnel dossierProfessionnel) {
        this.dossierProfessionnel = dossierProfessionnel;
    }
}
