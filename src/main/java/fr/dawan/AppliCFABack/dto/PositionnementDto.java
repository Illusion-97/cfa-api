package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.Positionnement.Niveau;

@SuppressWarnings("serial")

public class PositionnementDto extends BaseEntityDto implements Serializable {

	private Niveau niveauDebut;

	private Niveau niveauFin;

	private long interventionId;

	private long etudiantId;

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

	public long getInterventionId() {
		return interventionId;
	}

	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}

	public long getEtudiantId() {
		return etudiantId;
	}

	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}

}
