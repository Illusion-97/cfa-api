package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CompetenceProfessionnelle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String libelle;
	
	@Column(nullable = false)
	private byte numeroFiche;
	
	@ManyToMany(mappedBy = "competenceProfessionnelle")
	private List<Examen> examens;
	
	@ManyToOne
	private ActiviteType activiteType;

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

	public ActiviteType getActiviteType() {
		return activiteType;
	}

	public void setActiviteType(ActiviteType activiteType) {
		this.activiteType = activiteType;
	}
	
	
}
