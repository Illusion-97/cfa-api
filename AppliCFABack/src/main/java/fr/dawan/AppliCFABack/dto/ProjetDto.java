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
	private GroupeDto groupe;
	@XmlElement
	private PersonneDto personneReferent;
	@XmlElement
	private TypeProjet type;

	enum TypeProjet {
		ENTREPRISE, PEDAGOGIQUE
	}

	public ProjetDto() {
		super();
	}

	public ProjetDto(long id, String nom, String description, GroupeDto groupe, PersonneDto personneReferent,
			TypeProjet type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.groupe = groupe;
		this.personneReferent = personneReferent;
		this.type = type;
	}

	public long getIdProjetDto() {
		return id;
	}

	public void setIdProjetDto(long id) {
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
		return groupe;
	}

	public void setGroupeDto(GroupeDto groupe) {
		this.groupe = groupe;
	}

	public PersonneDto getPersonneReferent() {
		return personneReferent;
	}

	public void setPersonneReferent(PersonneDto personneReferent) {
		this.personneReferent = personneReferent;
	}

	public TypeProjet getType() {
		return type;
	}

	public void setType(TypeProjet type) {
		this.type = type;
	}

}
