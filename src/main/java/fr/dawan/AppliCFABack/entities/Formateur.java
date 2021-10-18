package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Formateur{
	
	@OneToOne
	private Utilisateur personne;
	
	@ManyToMany(mappedBy = "formateurs")
	private List<Intervention> interventions;

	public Formateur() {
		super();
	}

	public List<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public Utilisateur getPersonne() {
		return personne;
	}

	public void setPersonne(Utilisateur personne) {
		this.personne = personne;
	}


}
