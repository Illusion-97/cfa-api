package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Positionnement extends BaseEntity implements Serializable {

	@Enumerated(EnumType.STRING)
	private Niveau niveauDebut;

	@Enumerated(EnumType.STRING)
	private Niveau niveauFin;

	public enum Niveau {
		ABSENT(0, "#000000", "Absent"), AUCUNECONNAISSANCE(1, "#7E0021", "Aucune connaissance"),
		NOTIONS(2, "#FF3300", "Notions"), ENCOURSDACQUISITION(3, "#FFCC00", "En cours d'acquisition"),
		ACQUIS(4, "#FFFF66", "Acquis"), NIVEAUAVANCE(5, "#00FF66", "Niveau avancé");

		Niveau(int i, String string, String string2) {
			// TODO Auto-generated constructor stub
		}
	}

	@ManyToOne
	private Intervention intervention;

	@ManyToOne
	private Etudiant etudiant;

	public Niveau getNiveauDebut() {
		return niveauDebut;
	}

	public void setNiveauDebut(Niveau niveauDebut) {
		this.niveauDebut = niveauDebut;
	}

	public Niveau getNiveauFin() {
		return niveauFin;
	}

	public void setNiveauFin(Niveau niveauFin) {
		this.niveauFin = niveauFin;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
