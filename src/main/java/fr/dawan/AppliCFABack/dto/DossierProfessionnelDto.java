package fr.dawan.AppliCFABack.dto;

public class DossierProfessionnelDto {
	
	
	private long id;

	private String nom;
	
	private CursusDto cursusDto;
	
	public DossierProfessionnelDto() {
		// TODO Auto-generated constructor stub
	}
	public DossierProfessionnelDto(String nom) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
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
	public CursusDto getCursusDto() {
		return cursusDto;
	}
	public void setCursusDto(CursusDto cursusDto) {
		this.cursusDto = cursusDto;
	}
	

}
