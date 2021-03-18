package fr.dawan.AppliCFABack.entities;

import java.sql.Date;
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
public class Cours {

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
	private ProgrammeCours programmeCours;
	
	@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
	private List<Examen> examens;
	
	@OneToMany(mappedBy = "cours", cascade = CascadeType.ALL)
	private List<Devoir> devoirs;
		
	@ManyToMany
	private List<Promotion> promotions;
	
	@ManyToMany(mappedBy = "cours")
	private List<Formateur> formateurs;
	
	@Version
	private int version;

	public Cours() {
		super();
	}

	public Cours(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide/*,
			List<File> supportCours*/, ProgrammeCours programmeCours, List<Examen> examens, List<Devoir> devoirs,
			List<Promotion> promotions, List<Formateur> formateurs) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.noteInformation = noteInformation;
		this.noteEntraide = noteEntraide;
//		this.supportCours = supportCours;
		this.programmeCours = programmeCours;
		this.examens = examens;
		this.devoirs = devoirs;
		this.promotions = promotions;
		this.formateurs = formateurs;
	}
	
	public Cours(long id, Date dateDebut, Date dateFin) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
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
	public ProgrammeCours getProgrammeCours() {
		return programmeCours;
	}

	public void setProgrammeCours(ProgrammeCours programmeCours) {
		this.programmeCours = programmeCours;
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Formateur> getFormateur() {
		return formateurs;
	}

	public void setFormateur(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}
	
}
