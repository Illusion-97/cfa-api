package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.ContenuDossierProjet;

public class ContenuDossierProjetDto extends BaseEntityDto implements Serializable{

	private String contenu_projet;
	
    private long dossierProjetId;
    
    public ContenuDossierProjet toContenuProjet() {
        ContenuDossierProjet contenuProjet = new ContenuDossierProjet();
        contenuProjet.setContenu_projet(this.contenu_projet);
        return contenuProjet;
    }


	public String getContenu_projet() {
		return contenu_projet;
	}

	public void setContenu_projet(String contenu_projet) {
		this.contenu_projet = contenu_projet;
	}

	public long getDossierProjetId() {
		return dossierProjetId;
	}

	public void setDossierProjetId(long dossierProjetId) {
		this.dossierProjetId = dossierProjetId;
	}
    
    

}
