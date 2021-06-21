package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Formateur extends Utilisateur {
	@ManyToMany
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


}
