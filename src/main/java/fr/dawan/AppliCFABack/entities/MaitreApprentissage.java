package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class MaitreApprentissage extends Utilisateur {
	
	@ManyToOne
	private Entreprise entreprise;

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

	
}
