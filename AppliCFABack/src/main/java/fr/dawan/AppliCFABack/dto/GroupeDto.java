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
	private List<EtudiantDto> etudiants;
	@XmlElement
	private ProjetDto projet;

	public GroupeDto() {
		super();
	}
	
	public GroupeDto(long id, String nom, List<EtudiantDto> etudiants, ProjetDto projet) {
		super();
		this.id = id;
		this.nom = nom;
		this.etudiants = etudiants;
		this.projet = projet;
	}

	public long getIdGroupeDto() {
		return id;
	}

	public void setIdGroupeDto(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<EtudiantDto> getEtudiantDto() {
		return etudiants;
	}

	public void setEtudiantDto(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}

	public ProjetDto getProjetDto() {
		return projet;
	}

	public void setProjetDto(ProjetDto projet) {
		this.projet = projet;
	}

	
	
}
