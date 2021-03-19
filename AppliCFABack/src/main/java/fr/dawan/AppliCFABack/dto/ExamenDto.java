package fr.dawan.AppliCFABack.dto;

public class ExamenDto {
	private long id;
	private String enonce;
	private FormationDto formation;
	private CursusDto cursus;

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

	public FormationDto getFormation() {
		return formation;
	}

	public void setFormation(FormationDto formation) {
		this.formation = formation;
	}

	public CursusDto getCursus() {
		return cursus;
	}

	public void setCursus(CursusDto cursus) {
		this.cursus = cursus;
	}

}
