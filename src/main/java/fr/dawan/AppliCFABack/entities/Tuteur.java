package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.io.Serializable;
import java.util.List;



@SuppressWarnings("serial")
@Entity //unmapped prb
public class Tuteur extends BaseEntity implements Serializable{
	
	@OneToOne
	private Utilisateur utilisateur;
	
	
	@OneToMany(mappedBy = "tuteur")
	private List<Etudiant> etudiants;
	

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Tuteur() {
		super();
	}

	public Tuteur(Utilisateur utilisateur, List<Etudiant> etudiants) {
		super();
		this.utilisateur = utilisateur;
		
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
