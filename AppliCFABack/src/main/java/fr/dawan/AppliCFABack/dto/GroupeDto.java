package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "groupe")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupeDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String nom;
	@XmlElement
	private List<EtudiantDto> etudiantsDto;
	@XmlElement
	private ProjetDto projetDto;

	public GroupeDto() {
		super();
	}
	
	public GroupeDto(long id, String nom, List<EtudiantDto> etudiantDto, ProjetDto projetDto) {
		super();
		this.id = id;
		this.nom = nom;
<<<<<<< HEAD
		this.etudiantsDto = etudiants;
		this.projetDto = projet;
=======
		this.etudiants = etudiantDto;
		this.projet = projetDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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

<<<<<<< HEAD
	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
=======
	public List<EtudiantDto> getEtudiantDto() {
		return etudiants;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
=======
	public void setEtudiantDto(List<EtudiantDto> etudiantDto) {
		this.etudiants = etudiantDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public ProjetDto getProjetDto() {
<<<<<<< HEAD
		return projetDto;
=======
		return projet;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setProjetDto(ProjetDto projetDto) {
<<<<<<< HEAD
		this.projetDto = projetDto;
=======
		this.projet = projetDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	
}
