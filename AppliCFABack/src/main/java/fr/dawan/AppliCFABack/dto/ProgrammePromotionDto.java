package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmePromotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammePromotionDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Date dateDebut;
	@XmlElement
	private Date dateFin;
	@XmlElement
	private CentreDto centre;
	@XmlElement
	private PromotionDto promotion;
	@XmlElement
	private ReferentDto referent;
	@XmlElement
	private List<EtudiantDto> etudiants;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCours;

	public ProgrammePromotionDto() {
		super();
	}

	public ProgrammePromotionDto(long id, Date dateDebut, Date dateFin, CentreDto centre,
			PromotionDto promotionDto, ReferentDto referentDto, List<EtudiantDto> etudiantsDto,
			List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centre = centre;
		this.promotion = promotionDto;
		this.referent = referentDto;
		this.etudiants = etudiantsDto;
		this.programmeCours = programmeCoursDto;
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

	public PromotionDto getPromotionDto() {
		return promotion;
	}

	public void setPromotionDto(PromotionDto promotionDto) {
		this.promotion = promotionDto;
	}

	public ReferentDto getReferentDto() {
		return referent;
	}

	public void setReferentDto(ReferentDto referentDto) {
		this.referent = referentDto;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiants;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiants = etudiantsDto;
	}

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCours;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCours = programmeCoursDto;
	}
	
	
}
