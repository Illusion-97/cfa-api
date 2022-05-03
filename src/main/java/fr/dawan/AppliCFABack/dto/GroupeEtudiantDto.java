package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GroupeEtudiantDto extends BaseEntityDto implements Serializable {

	private String nom;
	private List<EtudiantDto> etudiantsDto;

	public GroupeEtudiantDto() {
		super();
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
