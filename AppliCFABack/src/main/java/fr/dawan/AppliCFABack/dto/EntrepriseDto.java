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
	private List<EtudiantDto> etudiantDto;

	public EntrepriseDto() {
		super();
	}

	public EntrepriseDto(long id, String nom, AdresseDto adresseDto, List<EtudiantDto> etudiantDto) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresseDto = adresseDto;
		this.etudiantDto = etudiantDto;
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
		return adresseDto;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}

	public List<EtudiantDto> getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(List<EtudiantDto> etudiantDto) {
		this.etudiantDto = etudiantDto;
	}
	
}
