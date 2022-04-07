package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Examen { // examen Java

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

//	@Column(nullable = false, length = 255)
//	private String enonce;
//
//	@ManyToOne
//	private Formation formation;
//
//	@ManyToOne
//	private Cursus cursus;
	
	@Column(nullable = false, length = 255)
	private String titre;
	
	@Lob
	private String descriptif;
	
	@Column(nullable = false, precision = 4, scale = 2)
	private double duree;
	
	@Column(nullable = false)
	private String pieceJointe;
	
	@Column(nullable = false)
	private LocalDate dateExamen; 
	
	@ManyToMany
	private List<ActiviteType> activiteTypes;
	
	@ManyToOne
	private Promotion promotion;
	
	@ManyToMany
	private Set<CompetenceProfessionnelle> competencesProfessionnelles;
	
	@OneToMany(mappedBy = "examen",cascade = CascadeType.ALL )
	private Set<Note> notes;
	


	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Examen() {
		super();
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}


	public LocalDate getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(LocalDate dateExamen) {
		this.dateExamen = dateExamen;
	}


	public List<ActiviteType> getActiviteType() {
		return activiteTypes;
	}

	public void setActiviteType(List<ActiviteType> activiteType) {
		this.activiteTypes = activiteType;
	}

	public Set<CompetenceProfessionnelle> getCompetencesProfessionnelles() {
		return competencesProfessionnelles;
	}
	
	
	public void setCompetencesProfessionnelles(Set<CompetenceProfessionnelle> competencesProfessionnelles) {
		this.competencesProfessionnelles = competencesProfessionnelles;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) { 
		this.id = id;
	}
	
	public List<Long> getActiviteTypesId(){
		List<Long> activityteTypesId = new ArrayList<Long>();
		for(ActiviteType at : activiteTypes) {
			
			if (at != null) {
				activityteTypesId.add(at.getId());
			}
		}
		return activityteTypesId;
	}
	public List<Long> getCompetencesProfessionnellesId(){
		List<Long> competencesProId = new ArrayList<Long>();
		for(CompetenceProfessionnelle cp : competencesProfessionnelles) {
			
			if (cp != null) {
				competencesProId.add(cp.getId());
			}
		}
		return competencesProId;
	}

//	public Examen(String enonce, Formation formation, Cursus cursus) {
//		super();
//		this.enonce = enonce;
//		this.formation = formation;
//		this.cursus = cursus;
//	}
	
//	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getEnonce() {
//		return enonce;
//	}
//
//	public void setEnonce(String enonce) {
//		this.enonce = enonce;
//	}
//
//	public Formation getFormation() {
//		return formation;
//	}
//
//	public void setFormation(Formation formation) {
//		this.formation = formation;
//	}
//
//	public Cursus getCursus() {
//		return cursus;
//	}
//
//	public void setCursus(Cursus cursus) {
//		this.cursus = cursus;
//	}

}
