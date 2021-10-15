package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Projet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String nom;

	@Column(nullable = false, length = 255)
	private String description;

	@ManyToOne
	private GroupeEtudiant groupe;

//	@Column(nullable = false, length = 255)
//	private TypeProjet type;
//	
//	enum TypeProjet{
//		ENTREPRISE, PEDAGOGIQUE
//	}

	public Projet() {
		super();
	}

	public Projet(String nom, String description, GroupeEtudiant groupe) {
		super();
		this.nom = nom;
		this.description = description;
		this.groupe = groupe;
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

	public GroupeEtudiant getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeEtudiant groupe) {
		this.groupe = groupe;
	}

}
