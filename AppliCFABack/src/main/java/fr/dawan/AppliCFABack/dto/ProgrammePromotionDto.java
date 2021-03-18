package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import fr.dawan.AppliCFABack.entities.Centre;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.ProgrammeCours;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Referent;

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
	private CentreDto centreDto;
	@XmlElement
	private PromotionDto promotionDto;
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private List<EtudiantDto> etudiantsDto;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCoursDto;

	public ProgrammePromotionDto() {
		super();
	}

	public ProgrammePromotionDto(long id, Date dateDebut, Date dateFin, CentreDto centreDto, PromotionDto promotionDto,
			ReferentDto referentDto, List<EtudiantDto> etudiantsDto, List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centreDto = centreDto;
		this.promotionDto = promotionDto;
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

	public void setCentreDto(CentreDto centreDto) {
		this.centreDto = centreDto;
	}

	public PromotionDto getPromotionDto() {
		return promotionDto;
	}

	public void setPromotionDto(PromotionDto promotionDto) {
		this.promotionDto = promotionDto;
	}

	public ReferentDto getReferentDto() {
		return referentDto;
	}

	public void setReferentDto(ReferentDto referentDto) {
		this.referentDto = referentDto;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
	}

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}


}
