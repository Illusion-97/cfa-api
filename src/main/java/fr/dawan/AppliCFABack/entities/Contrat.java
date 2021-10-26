package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contrat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
