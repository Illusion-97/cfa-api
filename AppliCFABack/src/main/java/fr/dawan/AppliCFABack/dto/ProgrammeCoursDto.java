package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmeCours")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammeCoursDto implements Serializable {
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
	private CoursDto coursDto;
	@XmlElement
	private List<ExamenDto> examensDto;
	@XmlElement
	private List<DevoirDto> devoirsDto;
	@XmlElement
	private List<ProgrammePromotionDto> programmePromotionsDto;
	@XmlElement
	private List<FormateurDto> formateursDto;

	
	public ProgrammeCoursDto() {
		super();
	}
	
	public ProgrammeCoursDto(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide,
			CoursDto coursDto, List<ExamenDto> examensDto, List<DevoirDto> devoirsDto,
			List<ProgrammePromotionDto> programmePromotionsDto, List<FormateurDto> formateursDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
		this.coursDto = coursDto;
		this.examensDto = examensDto;
		this.devoirsDto = devoirsDto;
		this.programmePromotionsDto = programmePromotionsDto;
		this.formateursDto = formateursDto;
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

	public CoursDto getCoursDto() {
		return coursDto;
	}

	public void setCoursDto(CoursDto coursDto) {
		this.coursDto = coursDto;
	}

	public List<ExamenDto> getExamensDto() {
		return examensDto;
	}

	public void setExamensDto(List<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

	public List<DevoirDto> getDevoirsDto() {
		return devoirsDto;
	}

	public void setDevoirsDto(List<DevoirDto> devoirsDto) {
		this.devoirsDto = devoirsDto;
	}

	public List<ProgrammePromotionDto> getProgrammePromotionsDto() {
		return programmePromotionsDto;
	}

	public void setProgrammePromotionsDto(List<ProgrammePromotionDto> programmePromotionsDto) {
		this.programmePromotionsDto = programmePromotionsDto;
	}

	public List<FormateurDto> getFormateursDto() {
		return formateursDto;
	}

	public void setFormateursDto(List<FormateurDto> formateursDto) {
		this.formateursDto = formateursDto;
	}


}
