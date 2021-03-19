package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class GroupeEtudiantDto {
	private long id;
	private String nom;
	private List<EtudiantDto> etudiants;

	public GroupeEtudiantDto() {
		super();
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

	public List<EtudiantDto> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}

}
