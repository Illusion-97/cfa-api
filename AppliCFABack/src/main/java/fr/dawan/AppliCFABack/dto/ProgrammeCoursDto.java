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
	private CoursDto cours;
	@XmlElement
	private List<ExamenDto> examens;
	@XmlElement
	private List<DevoirDto> devoirs;
	@XmlElement
	private List<ProgrammePromotionDto> programmePromotions;
	@XmlElement
	private List<FormateurDto> formateurs;

	
	public ProgrammeCoursDto() {
		super();
	}
	
	public ProgrammeCoursDto(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide,
			CoursDto CoursDto, List<ExamenDto> examenDto, List<DevoirDto> devoirDto,
			List<ProgrammePromotionDto> programmePromotionDto, List<FormateurDto> formateurDto) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
		this.cours = CoursDto;
		this.examens = examenDto;
		this.devoirs = devoirDto;
		this.programmePromotions = programmePromotionDto;
		this.formateurs = formateurDto;
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
		return cours;
	}

	public void setCoursDto(CoursDto coursDto) {
		this.cours = coursDto;
	}

	public List<ExamenDto> getExamenDto() {
		return examens;
	}

	public void setExamenDto(List<ExamenDto> examenDto) {
		this.examens = examenDto;
	}

	public List<DevoirDto> getDevoirDto() {
		return devoirs;
	}

	public void setDevoirDto(List<DevoirDto> devoirDto) {
		this.devoirs = devoirDto;
	}

	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotions;
	}

	public void setProgrammePromotionDto(List<ProgrammePromotionDto> programmePromotionDto) {
		this.programmePromotions = programmePromotionDto;
	}

	public List<FormateurDto> getFormateurDto() {
		return formateurs;
	}

	public void setFormateurDto(List<FormateurDto> formateurDto) {
		this.formateurs = formateurDto;
	}


}
