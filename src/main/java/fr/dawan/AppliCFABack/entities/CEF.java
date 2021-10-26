package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CEF{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
