package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Promotion extends BaseEntity implements Serializable { // CDA2021

	@Column(nullable = false, length = 255)
	private String nom;

	private LocalDate dateDebut; // 01/01/2021

	private LocalDate dateFin; // 31/12/2021
	

	private long idDg2;

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

	@ManyToMany(mappedBy = "promotions")
	private Set<Examen> examens;

	public Promotion() {
		super();
	}

	public Promotion(long id, String nom, LocalDate dateDebut, LocalDate dateFin, CEF cef, List<Etudiant> etudiants,
			List<Intervention> interventions, CentreFormation centreFormation, Utilisateur referentPedagogique,
			Cursus cursus) {
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

	public Set<Examen> getExamens() {
		return examens;
	}

	public void setExamens(Set<Examen> examens) {
		this.examens = examens;
	}

	/**
	 * @return le idDG2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDG2 le idDG2 à affecter
	 
	 */
	public void setIdDg2(long idDG2) {
		this.idDg2 = idDG2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(centreFormation, cursus, dateDebut, dateFin, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
//		System.out.println("CE : " +(centreFormation == null));
//		System.out.println(this.getIdDG2());
//		System.out.println("CEOther : " +(other.centreFormation == null));
//		System.out.println(other.getIdDG2());
//		System.out.println(centreFormation.getIdDg2() == other.centreFormation.getIdDg2());
//		System.out.println(cursus.getIdDg2() == other.cursus.getIdDg2());
//		System.out.println(Objects.equals(dateDebut, other.dateDebut));
//		System.out.println(Objects.equals(dateFin, other.dateFin));
//		System.out.println(Objects.equals(nom, other.nom));

		return
//				centreFormation.getIdDg2() == other.centreFormation.getIdDg2() &&
				cursus.getIdDg2() == other.cursus.getIdDg2()
				&& Objects.equals(dateDebut, other.dateDebut) && Objects.equals(dateFin, other.dateFin)
				&& Objects.equals(nom, other.nom);
	}

	@Override
	public String toString() {
		return "Promotion{" +
				"nom='" + nom + '\'' +
				", dateDebut=" + dateDebut +
				", dateFin=" + dateFin +
				", cef=" + cef +
				", etudiants=" + etudiants +
				", interventions=" + interventions +
				", centreFormation=" + centreFormation +
				", referentPedagogique=" + referentPedagogique +
				", cursus=" + cursus +
				", examens=" + examens +
				'}';
	}
}
