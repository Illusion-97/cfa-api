package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class ActiviteType extends BaseEntity implements Serializable{
	
	
	@Column(nullable = false)
	private String libelle;
	
	@Column(nullable = false)
	private byte numeroFiche;
	
	@ManyToMany(mappedBy = "activiteTypes" ,cascade = CascadeType.ALL )
	private List<Examen> examens;
	
	@OneToMany(mappedBy = "activiteType")
	private Set<CompetenceProfessionnelle> competenceProfessionnelles;
	
	@ManyToOne 
	private Cursus cursusActiviteType;	

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public byte getNumeroFiche() {
		return numeroFiche;
	}

	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	public List<Examen> getExamens() {
		return examens;
	}

	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	public Cursus getCursusActiviteType() {
		return cursusActiviteType;
	}

	public void setCursusActiviteType(Cursus cursusActiviteType) {
		this.cursusActiviteType = cursusActiviteType;
	}

	public Set<CompetenceProfessionnelle> getCompetenceProfessionnelles() {
		return competenceProfessionnelles;
	}

	public void setCompetenceProfessionnelles(Set<CompetenceProfessionnelle> competenceProfessionnelles) {
		this.competenceProfessionnelles = competenceProfessionnelles;
	}
	
	
}
