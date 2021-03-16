package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "cours")
@XmlAccessorType(XmlAccessType.FIELD)
public class CoursDto implements Serializable {

	private long id;

	private Date dateDebut;

	private Date dateFin;

	private String noteInformation;

	private String noteEntraide;

	private ProgrammeCoursDto programmeCours;

	private List<ExamenDto> examens;

	private List<DevoirDto> devoirs;

	private List<PromotionDto> promotions;

	private List<FormateurDto> formateur;

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

	public ProgrammeCoursDto getProgrammeCours() {
		return programmeCours;
	}

	public void setProgrammeCours(ProgrammeCoursDto programmeCours) {
		this.programmeCours = programmeCours;
	}

	public List<ExamenDto> getExamens() {
		return examens;
	}

	public void setExamens(List<ExamenDto> examens) {
		this.examens = examens;
	}

	public List<DevoirDto> getDevoirs() {
		return devoirs;
	}

	public void setDevoirs(List<DevoirDto> devoirs) {
		this.devoirs = devoirs;
	}

	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public List<FormateurDto> getFormateur() {
		return formateur;
	}

	public void setFormateur(List<FormateurDto> formateur) {
		this.formateur = formateur;
	}

}
