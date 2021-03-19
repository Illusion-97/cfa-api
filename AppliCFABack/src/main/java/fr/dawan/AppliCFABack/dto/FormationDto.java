package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class FormationDto {
	private long id;
	private String titre;
	private String contenu;
	private List<CursusDto> cursusLst;

	public FormationDto() {
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

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public List<CursusDto> getCursusLst() {
		return cursusLst;
	}

	public void setCursusLst(List<CursusDto> cursusLst) {
		this.cursusLst = cursusLst;
	}

}
