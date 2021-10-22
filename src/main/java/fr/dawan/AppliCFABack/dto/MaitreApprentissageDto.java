package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Utilisateur;

public class MaitreApprentissageDto extends UtilisateurDto{

	private long id;
	private EntrepriseDto entrepriseDto;
	private UtilisateurDto utilisateurDto;
	
	public MaitreApprentissageDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	
}
