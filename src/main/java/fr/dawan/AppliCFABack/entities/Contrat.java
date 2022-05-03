package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Contrat extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private LocalDate dateDebut;

	@Column(nullable = false)
	private LocalDate dateFin;

	@ManyToOne
	private MaitreApprentissage maitreApprentissage;
	@ManyToOne
	private Etudiant etudiant;

	public Contrat() {
		super();
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

	public MaitreApprentissage getMaitreApprentissage() {
		return maitreApprentissage;
	}

	public void setMaitreApprentissage(MaitreApprentissage maitreApprentissage) {
		this.maitreApprentissage = maitreApprentissage;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
