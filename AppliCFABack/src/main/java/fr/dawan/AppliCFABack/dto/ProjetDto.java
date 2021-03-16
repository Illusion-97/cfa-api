package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "projet")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjetDto implements Serializable {

	private long id;

	private String nom;

	private String description;

	private GroupeDto groupe;

	private PersonneDto personneReferent;

	private TypeProjet type;

	enum TypeProjet {
		ENTREPRISE, PEDAGOGIQUE
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

	public GroupeDto getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeDto groupe) {
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
