package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class MaitreApprentissage extends BaseEntity implements Serializable {

	@ManyToOne
	private Entreprise entreprise;
	@OneToOne
	private Utilisateur utilisateur;

	public MaitreApprentissage() {
		super();
	}

	public MaitreApprentissage(Entreprise entreprise) {
		super();
		this.entreprise = entreprise;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
