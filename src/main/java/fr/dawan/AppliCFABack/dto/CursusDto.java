package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CursusDto extends BaseEntityDto implements Serializable{
	
	private String titre;
	private List<FormationDto> formationsDto;
    private String description;
    private int duree;

	public CursusDto() {
		super();
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
