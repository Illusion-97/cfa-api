package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DossierProjetDto extends BaseEntityDto implements Serializable{
	
	private String nom;
		
	private ProjetDto projet;
	
	public DossierProjetDto() {
		// TODO Auto-generated constructor stub
	}
	public DossierProjetDto(String nom) {
		// TODO Auto-generated constructor stub
		this.nom=nom;
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
