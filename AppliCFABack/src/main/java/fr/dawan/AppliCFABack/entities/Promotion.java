package fr.dawan.AppliCFABack.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Promotion { // CDA2021

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String nom;

	@Temporal(value = TemporalType.DATE)
	private Date dateDebut; // 01/01/2021

	@Temporal(value = TemporalType.DATE)
	private Date dateFin; // 31/12/2021

	@ManyToOne
	private Utilisateur cef;

	@ManyToMany(mappedBy = "promotions")
	private List<Etudiant> etudiants;

	@ManyToOne
	private CentreFormation centreFormation; // lieuFormation

	@ManyToOne
	private Utilisateur referentPedagogique;

	@ManyToOne
	private Cursus cursus; // Dev Full Stack

	public Promotion() {
		super();
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

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Utilisateur getCef() {
		return cef;
	}

	public void setCef(Utilisateur cef) {
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

}
