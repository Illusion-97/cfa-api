package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@SuppressWarnings("serial")
@Entity
public class Projet extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@Column(nullable = false, length = 255)
	private String description;

	@ManyToOne
	private GroupeEtudiant groupe;

	

	public Projet() {
		super();
	}

	public Projet(String nom, String description, GroupeEtudiant groupe) {
		super();
		this.nom = nom;
		this.description = description;
		this.groupe = groupe;
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

	public GroupeEtudiant getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeEtudiant groupe) {
		this.groupe = groupe;
	}

}
