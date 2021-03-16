package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "promotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromotionDto implements Serializable {

	private long id;

	private Date dateDebut;

	private Date dateFin;

	private CentreDto centre;

	private ProgrammePromotionDto programmePromotion;

	private ReferentDto referent;

	private List<EtudiantDto> etudiants;

	private List<CoursDto> cours;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public CentreDto getCentre() {
		return centre;
	}

	public void setCentre(CentreDto centre) {
		this.centre = centre;
	}

	public ProgrammePromotionDto getProgrammePromotion() {
		return programmePromotion;
	}

	public void setProgrammePromotion(ProgrammePromotionDto programmePromotion) {
		this.programmePromotion = programmePromotion;
	}

	public ReferentDto getReferent() {
		return referent;
	}

	public void setReferent(ReferentDto referent) {
		this.referent = referent;
	}

	public List<EtudiantDto> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}

	public List<CoursDto> getCours() {
		return cours;
	}

	public void setCours(List<CoursDto> cours) {
		this.cours = cours;
	}
	
	
}
