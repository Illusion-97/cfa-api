package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class FichePoste extends BaseEntity implements Serializable {

	@Column(nullable = true, length = 255)
	private String intitule;

	@Column(nullable = true, length = 255)
	private String nature;

	@Column(nullable = true, length = 255)
	private String mission;

	@Column(nullable = true, length = 255)
	private String compositionService;

	@Column(nullable = true, length = 255)
	private String positionnement;

	@Column(nullable = true, length = 255)
	private String missionPrincipale;

	@OneToOne
	private Etudiant etudiant;

	public FichePoste() {
		super();
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getCompositionService() {
		return compositionService;
	}

	public void setCompositionService(String compositionService) {
		this.compositionService = compositionService;
	}

	public String getPositionnement() {
		return positionnement;
	}

	public void setPositionnement(String positionnement) {
		this.positionnement = positionnement;
	}

	public String getMissionPrincipale() {
		return missionPrincipale;
	}

	public void setMissionPrincipale(String missionPrincipale) {
		this.missionPrincipale = missionPrincipale;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
