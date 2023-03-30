package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Utilisateur;

@SuppressWarnings("serial")
public class TuteurDto extends BaseEntityDto implements Serializable{

	private UtilisateurDto utilisateurDto;
	//private List<Etudiant> etudiants;
	
	
	public TuteurDto() {
		super();
	}


	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}


	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}



	
}
