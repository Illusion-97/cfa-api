package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class CursusDto {
	private long id;
	private String titre;
	private List<FormationDto> formationsDto;
    private String description;
    private int duree;

	public CursusDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<FormationDto> getFormationsDto() {
		return formationsDto;
	}

	public void setFormationsDto(List<FormationDto> formationsDto) {
		this.formationsDto = formationsDto;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	
}
