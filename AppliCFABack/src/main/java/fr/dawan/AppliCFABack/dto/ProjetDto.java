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

	public ProjetDto(long id, String nom, String description, GroupeDto groupeDto, PersonneDto personneReferent,
			TypeProjet type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
<<<<<<< HEAD
		this.groupeDto = groupe;
		this.personneReferentDto = personneReferent;
=======
		this.groupe = groupeDto;
		this.personneReferent = personneReferent;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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
<<<<<<< HEAD
		return groupeDto;
=======
		return groupe;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setGroupeDto(GroupeDto groupe) {
		this.groupeDto = groupe;
=======
	public void setGroupeDto(GroupeDto groupeDto) {
		this.groupe = groupeDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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
