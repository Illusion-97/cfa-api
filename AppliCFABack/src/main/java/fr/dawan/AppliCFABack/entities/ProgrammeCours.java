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
	private List<ProgrammePromotion> programmePromotions;
	
	@ManyToMany(mappedBy = "programmeCours")
	private List<Formateur> formateurs;
	
	@Version
	private int version;

	public ProgrammeCours() {
		super();
	}

	public ProgrammeCours(long id, Date dateDebut, Date dateFin, String noteInformation, String noteEntraide/*,
			List<File> supportCours*/, Cours cours, List<Examen> examens, List<Devoir> devoirs,
<<<<<<< HEAD
			List<Promotion> promotions, List<Formateur> formateurs) {
=======
			List<ProgrammePromotion> programmePromotion, List<Formateur> formateur) {
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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
<<<<<<< HEAD
		this.promotions = promotions;
		this.formateurs = formateurs;
=======
		this.programmePromotions = programmePromotion;
		this.formateurs = formateur;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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
		return programmePromotions;
	}

	public void setProgrammePromotion(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotions = programmePromotion;
	}

	public List<Formateur> getFormateur() {
		return formateurs;
	}

<<<<<<< HEAD
	public void setFormateur(List<Formateur> formateurs) {
		this.formateurs = formateurs;
=======
	public void setFormateur(List<Formateur> formateur) {
		this.formateurs = formateur;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}
	
}
