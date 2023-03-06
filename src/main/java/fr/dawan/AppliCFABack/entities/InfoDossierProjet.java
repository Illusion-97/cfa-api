package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Anas
 *
 */
@Entity
public class InfoDossierProjet extends BaseEntity implements Serializable{

	 	@Column(nullable = false)
	    private String information_projet;

	    @ManyToOne
	    private DossierProjet dossierProjet;

	    
	    
		public String getInformation_projet() {
			return information_projet;
		}

		public void setInformation_projet(String information_projet) {
			this.information_projet = information_projet;
		}

		public DossierProjet getDossierProjet() {
			return dossierProjet;
		}

		public void setDossierProjet(DossierProjet dossierProjet) {
			this.dossierProjet = dossierProjet;
		}
		
}
