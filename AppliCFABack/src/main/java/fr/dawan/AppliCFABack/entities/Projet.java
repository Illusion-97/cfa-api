package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Projet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@Column(nullable = false, length = 255)
	private String description;
	
	@OneToOne
	private Groupe groupe;
	
	@ManyToOne
	private Personne personneReferent;
	
	@Column(nullable = false, length = 255)
	private TypeProjet type;
	
	enum TypeProjet{
		ENTREPRISE, PEDAGOGIQUE
	}
	
	@Version
	private int version;

	public Projet() {
		super();
	}

	public Projet(long id, String nom, String description, Groupe groupe, Personne personneReferent, TypeProjet type) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.groupe = groupe;
		this.personneReferent = personneReferent;
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

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Personne getUserReferent() {
		return personneReferent;
	}

	public void setUserReferent(Personne personneReferent) {
		this.personneReferent = personneReferent;
	}

	public TypeProjet getType() {
		return type;
	}

	public void setType(TypeProjet type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
