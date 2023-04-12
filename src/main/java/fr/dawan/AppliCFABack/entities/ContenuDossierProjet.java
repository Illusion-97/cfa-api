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
public class ContenuDossierProjet extends BaseEntity implements Serializable{

	 	@Column(nullable = false)
	 	
	    private String contenu_projet;

	    @ManyToOne
	    private DossierProjet dossierProjet;

	    
	    
		public String getContenu_projet() {
			return contenu_projet;
		}

		public void setContenu_projet(String contenu_projet) {
			this.contenu_projet = contenu_projet;
		}

		public DossierProjet getDossierProjet() {
			return dossierProjet;
		}

		public void setDossierProjet(DossierProjet dossierProjet) {
			this.dossierProjet = dossierProjet;
		}


}
