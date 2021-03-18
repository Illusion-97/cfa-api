package fr.dawan.AppliCFABack.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class ProgrammeCours{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private Date dateDebut;
	
	@Column(nullable = false, length = 255)
	private Date dateFin;
	
	@Column(nullable = false, length = 255)
	private String noteInformation;
	
	@Column(nullable = false, length = 255)
	private String noteEntraide;
	/*
	@Column(nullable = false, length = 255)
	private List<File> supportCours;
	*/
	
	@ManyToOne
	private Cours cours;
	
	@OneToMany(mappedBy = "programmeCours", cascade = CascadeType.ALL)
	private List<Examen> examens;
	
	@OneToMany(mappedBy = "programmeCours", cascade = CascadeType.ALL)
	private List<Devoir> devoirs;
		
	@ManyToMany
	private List<ProgrammePromotion> programmePromotion;
	
	@ManyToMany(mappedBy = "programmeCours")
	private List<Formateur> formateur;
	
	@Version
	private int version;

	public ProgrammeCours() {
		super();
	}

	public ProgrammeCours(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide/*,
			List<File> supportCours*/, Cours cours, List<Examen> examens, List<Devoir> devoirs,
			List<ProgrammePromotion> programmePromotion, List<Formateur> formateur) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
//		this.supportCours = supportCours;
		this.cours = cours;
		this.examens = examens;
		this.devoirs = devoirs;
		this.programmePromotion = programmePromotion;
		this.formateur = formateur;
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
/*
	public List<File> getSupportCours() {
		return supportCours;
	}

	public void setSupportCours(List<File> supportCours) {
		this.supportCours = supportCours;
	}
*/
	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public List<Examen> getExamens() {
		return examens;
	}

	public void setExamens(List<Examen> examens) {
		this.examens = examens;
	}

	public List<Devoir> getDevoirs() {
		return devoirs;
	}

	public void setDevoirs(List<Devoir> devoirs) {
		this.devoirs = devoirs;
	}

	public List<ProgrammePromotion> getProgrammePromotion() {
		return programmePromotion;
	}

	public void setProgrammePromotion(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotion = programmePromotion;
	}

	public List<Formateur> getFormateur() {
		return formateur;
	}

	public void setFormateur(List<Formateur> formateur) {
		this.formateur = formateur;
	}
	
}
