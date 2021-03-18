package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "projet")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjetDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String nom;
	@XmlElement
	private String description;
	@XmlElement
	private GroupeDto groupeDto;
	@XmlElement
	private PersonneDto personneReferentDto;
	@XmlElement
	private TypeProjet type;

	enum TypeProjet {
		ENTREPRISE, PEDAGOGIQUE
	}

	public ProjetDto() {
		super();
	}


	public ProjetDto(long id, String nom, String description, GroupeDto groupeDto, PersonneDto personneReferentDto,
			TypeProjet type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.groupeDto = groupeDto;
		this.personneReferentDto = personneReferentDto;
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public GroupeDto getGroupeDto() {
		return groupeDto;
	}


	public void setGroupeDto(GroupeDto groupeDto) {
		this.groupeDto = groupeDto;
	}


	public PersonneDto getPersonneReferentDto() {
		return personneReferentDto;
	}

	public void setPersonneReferentDto(PersonneDto personneReferent) {
		this.personneReferentDto = personneReferent;
	}

	public TypeProjet getType() {
		return type;
	}

	public void setType(TypeProjet type) {
		this.type = type;
	}

}
