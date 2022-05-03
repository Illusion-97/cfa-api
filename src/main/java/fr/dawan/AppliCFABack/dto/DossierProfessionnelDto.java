package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DossierProfessionnelDto extends BaseEntityDto implements Serializable{

	private String nom;
	
	private CursusDto cursusDto;
	
	public DossierProfessionnelDto() {
		// TODO Auto-generated constructor stub
	}
	public DossierProfessionnelDto(String nom) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
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
