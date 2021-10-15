package fr.dawan.AppliCFABack.dto;

public class ProjetDto {
	private long id;
	private String nom;
	private String description;
	private GroupeEtudiantDto groupeDto;

	public ProjetDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
