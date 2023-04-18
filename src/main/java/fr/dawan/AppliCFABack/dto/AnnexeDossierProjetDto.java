package fr.dawan.AppliCFABack.dto;


import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.AnnexeDossierProjet;

public class AnnexeDossierProjetDto extends BaseEntityDto implements Serializable {

    private String libelleAnnexe;
    
    private String pieceJointe;

    private long dossierProjetId;

    
    public String getPieceJointe() {
        return pieceJointe;
    }

    public void setPieceJointe(String pieceJointe) {
        this.pieceJointe = pieceJointe;
    } 

    public String getLibelleAnnexe() {
		return libelleAnnexe;
	}

	public void setLibelleAnnexe(String libelleAnnexe) {
		this.libelleAnnexe = libelleAnnexe;
	}

	public long getDossierProjetId() {
        return dossierProjetId;
    }

    public void setDossierProjetId(long dossierProjetId) {
        this.dossierProjetId = dossierProjetId;
    }
    
    

}
