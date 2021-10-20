package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Promotion { // CDA2021

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String nom;

	private LocalDate dateDebut; // 01/01/2021

	private LocalDate dateFin; // 31/12/2021

	@ManyToOne
	private CEF cef;

	@ManyToMany
	private List<Etudiant> etudiants;
	
	@ManyToMany(mappedBy = "promotions")
	private List<Intervention> interventions;

	@ManyToOne
	private CentreFormation centreFormation; // lieuFormation

	@ManyToOne
	private Utilisateur referentPedagogique;

	@ManyToOne
	private Cursus cursus; // Dev Full Stack

	public Promotion() {
		super();
	}

	public Promotion(long id, String nom, LocalDate dateDebut, LocalDate dateFin, CEF cef,
			List<Etudiant> etudiants, List<Intervention> interventions, CentreFormation centreFormation,
			Utilisateur referentPedagogique, Cursus cursus) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.cef = cef;
		this.etudiants = etudiants;
		this.interventions = interventions;
		this.centreFormation = centreFormation;
		this.referentPedagogique = referentPedagogique;
		this.cursus = cursus;
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

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public CEF getCef() {
		return cef;
	}

	public void setCef(CEF cef) {
		this.cef = cef;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public CentreFormation getCentreFormation() {
		return centreFormation;
	}

	public void setCentreFormation(CentreFormation centreFormation) {
		this.centreFormation = centreFormation;
	}

	public Utilisateur getReferentPedagogique() {
		return referentPedagogique;
	}

	public void setReferentPedagogique(Utilisateur referentPedagogique) {
		this.referentPedagogique = referentPedagogique;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

	public List<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}
	
}
