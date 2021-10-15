package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class GroupeEtudiantDto {
	private long id;
	private String nom;
	private List<EtudiantDto> etudiantsDto;

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

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	public void setEtudiants(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
	}

}
