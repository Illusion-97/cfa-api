package fr.dawan.AppliCFABack.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class DossierProjetDto {
	
	private long id;

	private String nom;
		
	private ProjetDto projet;
	
	public DossierProjetDto() {
		// TODO Auto-generated constructor stub
	}
	public DossierProjetDto(String nom) {
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
	public ProjetDto getProjet() {
		return projet;
	}
	public void setProjet(ProjetDto projet) {
		this.projet = projet;
	}
	
}
