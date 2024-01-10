package fr.dawan.AppliCFABack.entities;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
@Entity
public class Soutenance extends BaseEntity implements Serializable {

	// TODO Modif String par dateTime + ajouter lst<champsSupp>
	@OneToOne
	//@Column(nullable = true)
	private Etudiant etudiant;

	@Temporal(TemporalType.TIMESTAMP)
	private Date examDate;

	@Temporal(TemporalType.TIME)
	private Date minAccueil;

	@Temporal(TemporalType.TIME)
	private Date minEntretien;

	@Temporal(TemporalType.TIME)
	private Date minQuestion;

	@Temporal(TemporalType.TIME)
	private Date minEntretienFinal;

	@Temporal(TemporalType.TIME)
	private Date minDeliberation;


	public Soutenance() {
		super();
	}


	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getMinAccueil() {
		return minAccueil;
	}

	public void setMinAccueil(Date minAccueil) {
		this.minAccueil = minAccueil;
	}

	public Date getMinEntretien() {
		return minEntretien;
	}

	public void setMinEntretien(Date minEntretien) {
		this.minEntretien = minEntretien;
	}

	public Date getMinQuestion() {
		return minQuestion;
	}

	public void setMinQuestion(Date minQuestion) {
		this.minQuestion = minQuestion;
	}

	public Date getMinEntretienFinal() {
		return minEntretienFinal;
	}

	public void setMinEntretienFinal(Date minEntretienFinal) {
		this.minEntretienFinal = minEntretienFinal;
	}

	public Date getMinDeliberation() {
		return minDeliberation;
	}

	public void setMinDeliberation(Date minDeliberation) {
		this.minDeliberation = minDeliberation;
	}
}
