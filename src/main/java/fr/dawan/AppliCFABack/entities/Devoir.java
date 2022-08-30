package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Examen
 * @see Intervention
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class Devoir extends BaseEntity implements Serializable {

	@Column(nullable = false, columnDefinition = "TEXT")
	private String consigne;

	private LocalDateTime dateDebut;

	private LocalDateTime dateFin;

	@ManyToOne
	private Intervention intervention;
	
	@OneToMany(mappedBy = "devoir", cascade = CascadeType.ALL)
	private Set<DevoirEtudiant> devoirsEtudiant;

	/**
	 * @return la consigne
	 */
	public String getConsigne() {
		return consigne;
	}

	/**
	 * @param consigne la consigne à affecter
	 * 
	 */
	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}



	/**
	 * @return le devoirsEtudiant
	 */
	public Set<DevoirEtudiant> getDevoirsEtudiant() {
		return devoirsEtudiant;
	}

	/**
	 * @param devoirsEtudiant le devoirsEtudiant à affecter
	 
	 */
	public void setDevoirsEtudiant(Set<DevoirEtudiant> devoirsEtudiant) {
		this.devoirsEtudiant = devoirsEtudiant;
	}

	/**
	 * @return le dateDebut
	 */
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut le dateDebut à affecter
	 
	 */
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return le dateFin
	 */
	public LocalDateTime getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin le dateFin à affecter
	 
	 */
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return l'intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * @param intervention l'intervention à affecter
	 * 
	 */
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

}
