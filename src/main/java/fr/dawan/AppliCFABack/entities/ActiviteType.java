package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/***
 * 
 * @author Feres BG Valentin C.
 * @see Examen
 * @see CompetenceProfessionnelle
 * @see Cursus
 * @since 1.0
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
@Entity
public class ActiviteType extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private String libelle;

	@Column(nullable = false)
	private byte numeroFiche;

	@ManyToMany(mappedBy = "activiteTypes", cascade = CascadeType.ALL)
	private List<Examen> examens;

	@OneToMany(mappedBy = "activiteType", cascade = CascadeType.ALL)
	private Set<CompetenceProfessionnelle> competenceProfessionnelles;

	@ManyToOne
	private Cursus cursusActiviteType;

	/**
	 * @return le libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle le libelle à affecter
	 
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
	 
	 */
	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	/**
	 * @return les competenceProfessionnelles
	 */
	public Set<CompetenceProfessionnelle> getCompetenceProfessionnelles() {
		return competenceProfessionnelles;
	}

	/**
	 * @param competenceProfessionnelles les competenceProfessionnelles à affecter
	 
	 */
	public void setCompetenceProfessionnelles(Set<CompetenceProfessionnelle> competenceProfessionnelles) {
		this.competenceProfessionnelles = competenceProfessionnelles;
	}

	/**
	 * @return le cursusActiviteType
	 */
	public Cursus getCursusActiviteType() {
		return cursusActiviteType;
	}

	/**
	 * @param cursusActiviteType le cursusActiviteType à affecter
	 
	 */
	public void setCursusActiviteType(Cursus cursusActiviteType) {
		this.cursusActiviteType = cursusActiviteType;
	}





}
