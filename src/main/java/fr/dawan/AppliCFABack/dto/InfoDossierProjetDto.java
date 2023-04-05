package fr.dawan.AppliCFABack.dto;


import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.ContenuDossierProjet;
import fr.dawan.AppliCFABack.entities.DossierProjet;
import fr.dawan.AppliCFABack.entities.InfoDossierProjet;

public class InfoDossierProjetDto extends BaseEntityDto implements Serializable{
	
	private String information_projet;

 	private long dossierProjetId;

 	 public InfoDossierProjet toInfoProjet() {
 		InfoDossierProjet infoProjet = new InfoDossierProjet();
 		infoProjet.setInformation_projet(this.information_projet);
         return infoProjet;
     }
    
	public String getInformation_projet() {
		return information_projet;
	}

	public void setInformation_projet(String information_projet) {
		this.information_projet = information_projet;
	}

	public long getDossierProjetId() {
        return dossierProjetId;
    }

    public void setDossierProjetId(long dossierProjetId) {
        this.dossierProjetId = dossierProjetId;
    }
}
