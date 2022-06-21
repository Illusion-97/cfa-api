package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToOne;
import javax.persistence.*;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Examen
 * @see ActiviteType
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class CompetenceProfessionnelle extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private String libelle;

	@Column(nullable = false)
	private byte numeroFiche;

	@ManyToMany(mappedBy = "competencesProfessionnelles", fetch = FetchType.LAZY)
	private List<Examen> examens;

	@ManyToOne
	private ActiviteType activiteType;

	@OneToMany(mappedBy = "competenceProfessionnelle", cascade = CascadeType.ALL)
	private List<ExperienceProfessionnelle> experienceProfessionnelles;

	/**
	 * @return le libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle le libelle à affecter
	 * 
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return le numeroFiche
	 */
	public byte getNumeroFiche() {
		return numeroFiche;
	}

	/**
	 * @param numeroFiche le numeroFiche à affecter
	 * 
	 */
	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	/**
	 * @return les examens
	 */
	public List<Examen> getExamens() {
		return examens;
	}

	/**
	 * @param examens les examens à affecter
	 * 
	 */
	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	/**
	 * @return l' activiteType
	 */
	public ActiviteType getActiviteType() {
		return activiteType;
	}

	/**
	 * @param activiteType l' activiteType à affecter
	 * 
	 */
	public void setActiviteType(ActiviteType activiteType) {
		this.activiteType = activiteType;
	}

	public List<ExperienceProfessionnelle> getExperienceProfessionnelles() {
		return experienceProfessionnelles;
	}

	public void setExperienceProfessionnelles(List<ExperienceProfessionnelle> experienceProfessionnelles) {
		this.experienceProfessionnelles = experienceProfessionnelles;
	}
}
