package fr.dawan.AppliCFABack.entities;

import java.util.List;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Cours {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@Column(nullable = false, length = 255)
	private String description;
	
	@Column(nullable = false, length = 255)
	private long dure;
	
	@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
	private List<Examen> examens;
	
	@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
	private List<Devoir> devoirs;
		
	@ManyToMany
	private List<Promotion> promotions;
	
	@ManyToMany(mappedBy = "cours")
	private List<Formateur> formateurs;
	private List<ProgrammeCours> programmeCours;
	
	@Version
	private int version;

	public Cours() {
		super();
	}

	public Cours(long id, String nom, String description, long dure, List<ProgrammeCours> programmeCours) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dure = dure;
		this.programmeCours = programmeCours;
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

	public List<ProgrammeCours> getCours() {
		return programmeCours;
	}

	public void setCours(List<ProgrammeCours> programmeCours) {
		this.programmeCours = programmeCours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
