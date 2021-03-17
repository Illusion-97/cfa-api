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
	private AdresseDto adresse;
	@XmlElement
	private List<EtudiantDto> etudiants;

	public EntrepriseDto() {
		super();
	}

	public EntrepriseDto(long id, String nom, AdresseDto adresse, List<EtudiantDto> etudiants) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.etudiants = etudiants;
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

	public AdresseDto getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseDto adresse) {
		this.adresse = adresse;
	}

	public List<EtudiantDto> getEtudiant() {
		return etudiants;
	}

	public void setEtudiant(List<EtudiantDto> etudiantDto) {
		this.etudiants = etudiantDto;
	}
	
}
