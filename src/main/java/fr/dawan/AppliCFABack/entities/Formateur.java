package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Formateur{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
