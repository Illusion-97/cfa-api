package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "cours")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoursDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private Date dateDebut;
	@XmlElement
	private Date dateFin;
	@XmlElement
	private String noteInformation;
	@XmlElement
	private String noteEntraide;
	@XmlElement
	private ProgrammeCoursDto programmeCoursDto;
	@XmlElement
	private List<ExamenDto> examenDto;
	@XmlElement
	private List<DevoirDto> devoirDto;
	@XmlElement
	private List<PromotionDto> promotionDto;
	@XmlElement
	private List<FormateurDto> formateurDto;

	
	public CoursDto() {
		super();
	}
	
	public CoursDto(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide,
			ProgrammeCoursDto programmeCoursDto, List<ExamenDto> examenDto, List<DevoirDto> devoirDto,
			List<PromotionDto> promotionDto, List<FormateurDto> formateurDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
		this.programmeCoursDto = programmeCoursDto;
		this.examenDto = examenDto;
		this.devoirDto = devoirDto;
		this.promotionDto = promotionDto;
		this.formateurDto = formateurDto;
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

	public String getNoteInformation() {
		return noteInformation;
	}

	public void setNoteInformation(String noteInformation) {
		this.noteInformation = noteInformation;
	}

	public String getNoteEntraide() {
		return noteEntraide;
	}

	public void setNoteEntraide(String noteEntraide) {
		this.noteEntraide = noteEntraide;
	}

	public ProgrammeCoursDto getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(ProgrammeCoursDto programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}

	public List<ExamenDto> getExamenDto() {
		return examenDto;
	}

	public void setExamenDto(List<ExamenDto> examenDto) {
		this.examenDto = examenDto;
	}

	public List<DevoirDto> getDevoirDto() {
		return devoirDto;
	}

	public void setDevoirDto(List<DevoirDto> devoirDto) {
		this.devoirDto = devoirDto;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotionDto;
	}

	public void setPromotionDto(List<PromotionDto> promotionDto) {
		this.promotionDto = promotionDto;
	}

	public List<FormateurDto> getFormateurDto() {
		return formateurDto;
	}

	public void setFormateurDto(List<FormateurDto> formateurDto) {
		this.formateurDto = formateurDto;
	}


}
