package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class CEF extends BaseEntity implements Serializable{
	
	
	@OneToOne
	private Utilisateur utilisateur;

	@ManyToOne
	private CentreFormation centreFormation;
	
	@ManyToOne
	private Entreprise entreprise;

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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
