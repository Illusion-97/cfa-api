package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class CompetenceProfessionnelle extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private String libelle;

	@Column(nullable = false)
	private byte numeroFiche;

	@ManyToMany(mappedBy = "competencesProfessionnelles")
	private List<Examen> examens;

	@ManyToOne
	private ActiviteType activiteType;

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
