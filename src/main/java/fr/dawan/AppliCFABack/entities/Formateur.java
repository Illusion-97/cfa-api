package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Formateur extends BaseEntity implements Serializable {

	@OneToOne(cascade = CascadeType.ALL)
	private Utilisateur utilisateur;

	@ManyToMany(mappedBy = "formateurs")
	private List<Intervention> interventions;

	public Formateur() {
		super();
	}

	public Formateur(long id, Utilisateur utilisateur, Entreprise entreprise, List<Intervention> interventions) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.interventions = interventions;
	}



	public List<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
