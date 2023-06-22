package fr.dawan.AppliCFABack.dto;


import java.io.File;
import java.io.Serializable;

public class AnnexeDto extends BaseEntityDto implements Serializable {

    private String libelleAnnexe;

    private File pieceJointe;

    private long dossierProfessionnelId;


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

	public long getDossierProfessionnelId() {
		return dossierProfessionnelId;
	}

	public void setDossierProfessionnelId(long dossierProfessionnelId) {
        this.dossierProfessionnelId = dossierProfessionnelId;
    }
}
