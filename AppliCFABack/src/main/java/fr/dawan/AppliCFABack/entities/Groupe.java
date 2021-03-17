package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@ManyToMany
	private List<Etudiant> etudiants;
	
	@OneToOne
	private Projet projet;
	
	@Version
	private int version;

	public Groupe() {
		super();
	}

	public Groupe(long id, String nom, List<Etudiant> etudiants, Projet projet) {
		super();
		this.id = id;
		this.nom = nom;
		this.etudiants = etudiants;
		this.projet = projet;
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

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
