package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "entreprise")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrepriseDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String nom;
	@XmlElement
	private AdresseDto adresseDto;
	@XmlElement
<<<<<<< HEAD
	private List<EtudiantDto> etudiantsDto;
=======
	private List<EtudiantDto> etudiant;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack

	public EntrepriseDto() {
		super();
	}

	public EntrepriseDto(long id, String nom, AdresseDto adresseDto, List<EtudiantDto> etudiantDto) {
		super();
		this.id = id;
		this.nom = nom;
<<<<<<< HEAD
		this.adresseDto = adresse;
		this.etudiantsDto = etudiants;
=======
		this.adresse = adresseDto;
		this.etudiant = etudiantDto;
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

	public AdresseDto getAdresseDto() {
<<<<<<< HEAD
		return adresseDto;
=======
		return adresse;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setAdresseDto(AdresseDto adresseDto) {
<<<<<<< HEAD
		this.adresseDto = adresseDto;
=======
		this.adresse = adresseDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
=======
	public List<EtudiantDto> getEtudiantDto() {
		return etudiant;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
=======
	public void setEtudiantDto(List<EtudiantDto> etudiantDto) {
		this.etudiant = etudiantDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	
}
