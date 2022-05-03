package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FormationDto extends BaseEntityDto implements Serializable {

	private String titre;
	private String contenu;
	private List<CursusDto> cursusLstDto;
	private long idDg2;
	private String duration;
	private String slug;

	public FormationDto() {
		super();
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

	public List<CursusDto> getCursusLstDto() {
		return cursusLstDto;
	}

	public void setCursusLstDto(List<CursusDto> cursusLstDto) {
		this.cursusLstDto = cursusLstDto;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}
