package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "promotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromotionDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Date dateDebut;
	@XmlElement
	private Date dateFin;
	@XmlElement
	private CentreDto centre;
	@XmlElement
	private ProgrammePromotionDto programmePromotion;
	@XmlElement
	private ReferentDto referent;
	@XmlElement
	private List<EtudiantDto> etudiants;
	@XmlElement
	private List<CoursDto> cours;

	public PromotionDto() {
		super();
	}

	public PromotionDto(long id, Date dateDebut, Date dateFin, CentreDto centre,
			ProgrammePromotionDto programmePromotion, ReferentDto referent, List<EtudiantDto> etudiants,
			List<CoursDto> cours) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centre = centre;
		this.programmePromotion = programmePromotion;
		this.referent = referent;
		this.etudiants = etudiants;
		this.cours = cours;
	}

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
