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
public class ProgrammePromotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private Date dateDebut;
	
	@Column(nullable = false, length = 255)
	private Date dateFin;
	
	@ManyToOne
	private Centre centre;
	
//	@ManyToOne
//	private Promotion promotion;
	
	@ManyToOne
	private Referent referent;
	
	@ManyToMany
	private List<Etudiant> etudiants;
	
//	@ManyToMany(mappedBy = "programmePromotions")
//	private List<ProgrammeCours> programmeCours;
	
	@Version
	private int version;

	public ProgrammePromotion() {
		super();
	}

	public ProgrammePromotion(long id, Date dateDebut, Date dateFin, Centre centre, Promotion promotion,
			List<Etudiant> etudiants, List<ProgrammeCours> programmeCours, Referent referent) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centre = centre;
//		this.promotion = promotion;
		this.etudiants = etudiants;
//		this.programmeCours = programmeCours;
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

//	public Promotion getPromotion() {
//		return promotion;
//	}
//
//	public void setPromotion(Promotion promotion) {
//		this.promotion = promotion;
//	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

//	public List<ProgrammeCours> getProgrammeCours() {
//		return programmeCours;
//	}
//
//	public void setProgrammeCours(List<ProgrammeCours> programmeCours) {
//		this.programmeCours = programmeCours;
//	}

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
