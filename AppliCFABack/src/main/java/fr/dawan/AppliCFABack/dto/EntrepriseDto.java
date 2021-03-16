package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "entreprise")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntrepriseDto implements Serializable {

	private long id;

	private String nom;

	private AdresseDto adresse;

	private List<EtudiantDto> etudiants;

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

	public List<EtudiantDto> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}
	
	
}
