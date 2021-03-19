package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class CursusDto {
	private long id;
	private String titre;
	private List<FormationDto> formations;

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

	public List<FormationDto> getFormations() {
		return formations;
	}

	public void setFormations(List<FormationDto> formations) {
		this.formations = formations;
	}

}
