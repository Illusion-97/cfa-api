package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class DevoirEtudiant extends BaseEntity implements Serializable {

	@ManyToOne
	private Devoir devoir;

	@ManyToOne
	private Etudiant etudiant;

	private LocalDateTime dateRendu;

	private String pieceJointe;

	public Devoir getDevoir() {
		return devoir;
	}

	public void setDevoir(Devoir devoir) {
		this.devoir = devoir;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public LocalDateTime getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(LocalDateTime dateRendu) {
		this.dateRendu = dateRendu;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

}
