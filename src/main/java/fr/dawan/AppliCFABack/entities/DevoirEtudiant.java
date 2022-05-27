package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Etudiant
 * @see Devoir
 * @see Intervention
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"devoir_id", "etudiant_id" })  )
public class DevoirEtudiant extends BaseEntity implements Serializable {

	@ManyToOne
	private Devoir devoir;
	@ManyToOne
	private Etudiant etudiant;

	private LocalDateTime dateRendu;

	private String pieceJointe;

	
	/**
	 * @return l'etudiant
	 */
	public Etudiant getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant l'etudiant à affecter
	 * 
	 */
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return la dateRendu
	 */
	public LocalDateTime getDateRendu() {
		return dateRendu;
	}

	/**
	 * @param dateRendu la dateRendu à affecter
	 * 
	 */
	public void setDateRendu(LocalDateTime dateRendu) {
		this.dateRendu = dateRendu;
	}

	/**
	 * @return la pieceJointe
	 */
	public String getPieceJointe() {
		return pieceJointe;
	}

	/**
	 * @param pieceJointe la pieceJointe à affecter
	 * 
	 */
	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

	/**
	 * @param devoir le devoir à affecter
	 * 
	 */
	public void setDevoir(Devoir devoir) {
		this.devoir = devoir;
	}

	/**
	 * @return le devoir
	 */
	public Devoir getDevoir() {
		return devoir;
	}

}
