package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.ResumeDossierProjet;

public class ResumeDossierProjetDto extends BaseEntityDto implements Serializable{

	private String resume_projet;
	
    private long dossierProjetId;

	 public ResumeDossierProjet toResumeProjet() {
		ResumeDossierProjet ResumeProjet = new ResumeDossierProjet();
		ResumeProjet.setResume_projet(this.resume_projet);
        return ResumeProjet;
    }
    
	public String getResume_projet() {
		return resume_projet;
	}

	public void setResume_projet(String resume_projet) {
		this.resume_projet = resume_projet;
	}

	public long getDossierProjetId() {
		return dossierProjetId;
	}

	public void setDossierProjetId(long dossierProjetId) {
		this.dossierProjetId = dossierProjetId;
	}

    
}
