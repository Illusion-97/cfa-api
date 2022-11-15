package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	 @ManyToMany(mappedBy="competencesEvaluees")
	 private List<EvaluationFormation> evaluationsFormations;

//	@OneToMany(mappedBy = "competenceProfessionnelle", cascade = CascadeType.ALL)
//	private Set<CompetenceExperienceEtudiant> competenceExperienceEtudiants;

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

//	public Set<CompetenceExperienceEtudiant> getCompetenceExperienceEtudiants() {
//		return competenceExperienceEtudiants;
//	}
//
//	public void setCompetenceExperienceEtudiants(Set<CompetenceExperienceEtudiant> competenceExperienceEtudiants) {
//		this.competenceExperienceEtudiants = competenceExperienceEtudiants;
//	}

		public List<ExperienceProfessionnelle> getExperienceProfessionnelles() {
		return experienceProfessionnelles;
	}

	public void setExperienceProfessionnelles(List<ExperienceProfessionnelle> experienceProfessionnelles) {
		this.experienceProfessionnelles = experienceProfessionnelles;
	}
}
