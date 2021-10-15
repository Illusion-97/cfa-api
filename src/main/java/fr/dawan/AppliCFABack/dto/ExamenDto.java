package fr.dawan.AppliCFABack.dto;

public class ExamenDto {
	private long id;
	private String enonce;
	private FormationDto formationDto;
	private CursusDto cursusDto;

	public ExamenDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public FormationDto getFormationDto() {
		return formationDto;
	}

	public void setFormationDto(FormationDto formationDto) {
		this.formationDto = formationDto;
	}

	public CursusDto getCursusDto() {
		return cursusDto;
	}

	public void setCursusDto(CursusDto cursusDto) {
		this.cursusDto = cursusDto;
	}

}
