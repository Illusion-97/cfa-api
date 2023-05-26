package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class CEF extends BaseEntity implements Serializable{
	
	
	@OneToOne
	private Utilisateur utilisateur;



	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
