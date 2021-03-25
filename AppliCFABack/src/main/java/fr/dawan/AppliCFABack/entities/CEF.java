package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CEF extends Utilisateur {

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

}
