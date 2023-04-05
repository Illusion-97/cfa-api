package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ResumeDossierProjet extends BaseEntity implements Serializable{

	@Column(nullable = false)
    private String resume_projet;

    @ManyToOne
    private DossierProjet dossierProjet;

    
    
	public String getResume_projet() {
		return resume_projet;
	}

	public void setResume_projet(String resume_projet) {
		this.resume_projet = resume_projet;
	}

	public DossierProjet getDossierProjet() {
		return dossierProjet;
	}

	public void setDossierProjet(DossierProjet dossierProjet) {
		this.dossierProjet = dossierProjet;
	}

}
