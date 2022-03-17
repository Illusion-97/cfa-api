package fr.dawan.AppliCFABack.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ActiviteType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String libelle;
	
	@Column(nullable = false)
	private byte numeroFiche;
	
	@OneToMany(mappedBy = "activiteType" ,cascade = CascadeType.ALL )
	private List<Examen> examens;
	
	@ManyToOne 
	private Cursus cursusActiviteType;
	
	@OneToMany(mappedBy = "activiteType")
	private Set<CompetenceProfessionnelle> competenceProfessionnelles;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
