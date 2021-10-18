package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CEF{
	
	@OneToOne
	private Utilisateur personne;

	@ManyToOne
	private CentreFormation centreFormation;

	public CEF() {
		super();
	}

	public CEF(CentreFormation centreFormation) {
		super();
		this.centreFormation = centreFormation;
	}

	public CentreFormation getCentreFormation() {
		return centreFormation;
	}

	public void setCentreFormation(CentreFormation centreFormation) {
		this.centreFormation = centreFormation;
	}

	public Utilisateur getPersonne() {
		return personne;
	}

	public void setPersonne(Utilisateur personne) {
		this.personne = personne;
	}

}
