package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@OneToOne
	private Adresse adresse;
	
	@OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
	private List<Etudiant> etudiants;
	
	@Version
	private int version;

	public Entreprise() {
		super();
	}

	public Entreprise(long id, String nom, Adresse adresse, List<Etudiant> etudiants) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.etudiants = etudiants;
	}

	public long getIdEntreprise() {
		return id;
	}

	public void setIdEntreprise(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
