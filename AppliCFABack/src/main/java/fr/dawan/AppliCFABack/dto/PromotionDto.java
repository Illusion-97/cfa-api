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
	private CentreDto centreDto;
	@XmlElement
	private ProgrammePromotionDto programmePromotionDto;
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private List<EtudiantDto> etudiantsDto;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCoursDto;

	public PromotionDto() {
		super();
	}

	public PromotionDto(long id, Date dateDebut, Date dateFin, CentreDto centre,
			ProgrammePromotionDto programmePromotionDto, ReferentDto referentDto, List<EtudiantDto> etudiantsDto,
			List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centreDto = centre;
		this.programmePromotionDto = programmePromotionDto;
		this.referentDto = referentDto;
		this.etudiantsDto = etudiantsDto;
		this.programmeCoursDto = programmeCoursDto;
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

	public CentreDto getCentreDto() {
		return centreDto;
	}

	public void setCentreDto(CentreDto centre) {
		this.centreDto = centre;
	}

	public ProgrammePromotionDto getProgrammePromotionDto() {
		return programmePromotionDto;
	}

	public void setProgrammePromotionDto(ProgrammePromotionDto programmePromotion) {
		this.programmePromotionDto = programmePromotion;
	}

	public ReferentDto getReferentDto() {
		return referentDto;
	}

	public void setReferentDto(ReferentDto referent) {
		this.referentDto = referent;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiants) {
		this.etudiantsDto = etudiants;
	}

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}
	
	
}
