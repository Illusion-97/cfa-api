package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Utilisateur;

@SuppressWarnings("serial")
public class TuteurDto extends BaseEntityDto implements Serializable{

	private Utilisateur utilisateur;
	//private List<Etudiant> etudiants;
	
	
	public TuteurDto() {
		super();
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	
}
