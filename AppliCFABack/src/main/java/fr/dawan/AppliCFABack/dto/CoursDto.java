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
	private ProgrammeCoursDto programmeCours;
	@XmlElement
	private List<ExamenDto> examens;
	@XmlElement
	private List<DevoirDto> devoirs;
	@XmlElement
	private List<PromotionDto> promotions;
	@XmlElement
	private List<FormateurDto> formateurs;

	
	public CoursDto() {
		super();
	}
	
	public CoursDto(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide,
			ProgrammeCoursDto programmeCours, List<ExamenDto> examens, List<DevoirDto> devoirs,
			List<PromotionDto> promotions, List<FormateurDto> formateurs) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
		this.programmeCours = programmeCours;
		this.examens = examens;
		this.devoirs = devoirs;
		this.promotions = promotions;
		this.formateurs = formateurs;
	}

	public long getIdCoursDto() {
		return id;
	}

	public void setIdCoursDto(long id) {
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
		return programmeCours;
	}

	public void setProgrammeCoursDto(ProgrammeCoursDto programmeCours) {
		this.programmeCours = programmeCours;
	}

	public List<ExamenDto> getExamenDto() {
		return examens;
	}

	public void setExamenDto(List<ExamenDto> examens) {
		this.examens = examens;
	}

	public List<DevoirDto> getDevoirDto() {
		return devoirs;
	}

	public void setDevoirDto(List<DevoirDto> devoirs) {
		this.devoirs = devoirs;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotions;
	}

	public void setPromotionDto(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public List<FormateurDto> getFormateurDto() {
		return formateurs;
	}

	public void setFormateurDto(List<FormateurDto> formateurs) {
		this.formateurs = formateurs;
	}


}
