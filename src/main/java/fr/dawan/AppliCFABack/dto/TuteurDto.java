package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TuteurDto extends BaseEntityDto implements Serializable{

	private UtilisateurDto utilisateurDto;

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
