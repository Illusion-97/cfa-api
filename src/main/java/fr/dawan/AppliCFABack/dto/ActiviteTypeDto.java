package fr.dawan.AppliCFABack.dto;

import java.util.List;

import fr.dawan.AppliCFABack.entities.Cursus;
import fr.dawan.AppliCFABack.entities.Examen;

public class ActiviteTypeDto {

	private long id;
	private long libelle;
	private byte numeroFiche;
	private List<Examen> examensDto;
	private CursusDto cursusActiviteDto;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getLibelle() {
		return libelle;
	}
	public void setLibelle(long libelle) {
		this.libelle = libelle;
	}
	public byte getNumeroFiche() {
		return numeroFiche;
	}
	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}
	public List<Examen> getExamensDto() {
		return examensDto;
	}
	public void setExamensDto(List<Examen> examensDto) {
		this.examensDto = examensDto;
	}
	public CursusDto getCursusActiviteDto() {
		return cursusActiviteDto;
	}
	public void setCursusActiviteDto(CursusDto cursusActiviteDto) {
		this.cursusActiviteDto = cursusActiviteDto;
	}

	
}
