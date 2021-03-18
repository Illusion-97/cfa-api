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
	
	public GroupeDto(long id, String nom, List<EtudiantDto> etudiantDto, ProjetDto projetDto) {
		super();
		this.id = id;
		this.nom = nom;
		this.etudiants = etudiantDto;
		this.projet = projetDto;
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

	public List<EtudiantDto> getEtudiantDto() {
		return etudiants;
	}

	public void setEtudiantDto(List<EtudiantDto> etudiantDto) {
		this.etudiants = etudiantDto;
	}

	public ProjetDto getProjetDto() {
		return projet;
	}

	public void setProjetDto(ProjetDto projetDto) {
		this.projet = projetDto;
	}

	
	
}
