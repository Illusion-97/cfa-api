package fr.dawan.AppliCFABack.dto;


import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class AnnexeDto extends BaseEntityDto implements Serializable {

    private String libelle;

    private String pieceJointe;

    private long dossierProfessionnelId;

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

	public long getDossierProfessionnelId() {
        return dossierProfessionnelId;
    }

    public void setDossierProfessionnelId(long dossierProfessionnelId) {
        this.dossierProfessionnelId = dossierProfessionnelId;
    }
}
