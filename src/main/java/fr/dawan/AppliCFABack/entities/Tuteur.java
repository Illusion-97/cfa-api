package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@SuppressWarnings("serial")
@Entity
public class Tuteur extends BaseEntity implements Serializable{
	
	@OneToOne
	private Utilisateur utilisateur;
	
	@OneToMany(mappedBy = "tuteurs")
	private List<Etudiant> etudiants;
	

	public Tuteur() {
		super();
	}

	public Tuteur(Utilisateur utilisateur, List<Etudiant> etudiants) {
		super();
		this.utilisateur = utilisateur;
		this.etudiants = etudiants;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	
	

}
