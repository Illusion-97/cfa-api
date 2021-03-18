package fr.dawan.AppliCFABack.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private Date dateDebut;
	
	@Column(nullable = false, length = 255)
	private Date dateFin;
	
	@ManyToOne
	private Centre centre;
	
	@ManyToOne
	private ProgrammePromotion programmePromotion;
	
	@ManyToOne
	private Referent referent;
	
	@ManyToMany
	private List<Etudiant> etudiants;
	
	@ManyToMany(mappedBy = "promotions")
	private List<ProgrammeCours> programmeCours;
	
	@Version
	private int version;

	public Promotion() {
		super();
	}

	public Promotion(long id, Date dateDebut, Date dateFin, Centre centre, ProgrammePromotion promotion,
			List<Etudiant> etudiants, List<ProgrammeCours> programmeCours, Referent referent) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centre = centre;
		this.programmePromotion = promotion;
		this.etudiants = etudiants;
		this.programmeCours = programmeCours;
		this.referent = referent;
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

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public ProgrammePromotion getProgrammePromotion() {
		return programmePromotion;
	}

	public void setProgrammePromotion(ProgrammePromotion promotion) {
		this.programmePromotion = promotion;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<ProgrammeCours> getProgrammeCours() {
		return programmeCours;
	}

	public void setProgrammeCours(List<ProgrammeCours> programmeCours) {
		this.programmeCours = programmeCours;
	}

	public Referent getReferent() {
		return referent;
	}

	public void setReferent(Referent referent) {
		this.referent = referent;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
