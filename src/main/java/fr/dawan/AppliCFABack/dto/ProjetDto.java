package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProjetDto extends BaseEntityDto implements Serializable {

	private String nom;
	private String description;
	private GroupeEtudiantDto groupeDto;

	public ProjetDto() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GroupeEtudiantDto getGroupeDto() {
		return groupeDto;
	}

	public void setGroupe(GroupeEtudiantDto groupeDto) {
		this.groupeDto = groupeDto;
	}

}
