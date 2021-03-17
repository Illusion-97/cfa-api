package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class ProgrammeCours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@Column(nullable = false, length = 255)
	private String description;
	
	@Column(nullable = false, length = 255)
	private long dure;
	
	@OneToMany(mappedBy = "programmeCours", cascade = CascadeType.ALL)
	private List<Cours> cours;
	
	@Version
	private int version;

	public ProgrammeCours() {
		super();
	}

	public ProgrammeCours(long id, String nom, String description, long dure, List<Cours> cours) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dure = dure;
		this.cours = cours;
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

	public long getDure() {
		return dure;
	}

	public void setDure(long dure) {
		this.dure = dure;
	}

	public List<Cours> getCours() {
		return cours;
	}

	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
