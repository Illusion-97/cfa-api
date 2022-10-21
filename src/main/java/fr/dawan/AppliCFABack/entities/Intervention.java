package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.BatchSize;

@SuppressWarnings("serial")
@Entity
@BatchSize(size = 20) //permet un gain de temps à tester
public class Intervention extends BaseEntity implements Serializable { // intervention prévue

	//	@Temporal(value = TemporalType.DATE)
	private LocalDate dateDebut; // 12/03

//	@Temporal(value = TemporalType.DATE)
	private LocalDate dateFin; // 18/03

	@Column(nullable = true) // id dans dg2
	private long idDg2;
	
	@ManyToOne
	private Formation formation; // Java init

	@ManyToOne
	private Intervention interventionMere; // Java init+appro
	

	@ManyToMany
	private List<Promotion> promotions; // CDA 2021

	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Formateur> formateurs;

	@ManyToMany(mappedBy = "interventions")
	private List<SupportCours> supportsCours;

	@Column
	private String noteInfoPersonnel;

	public Intervention() {
		super();
	}

	public Intervention(LocalDate dateDebut, LocalDate dateFin, Formation formation, Intervention interventionMere,
			List<Promotion> promotions) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.formation = formation;
		this.interventionMere = interventionMere;
		this.promotions = promotions;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Intervention getInterventionMere() {
		return interventionMere;
	}

	public void setInterventionMere(Intervention interventionMere) {
		this.interventionMere = interventionMere;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(List<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public String getNoteInfoPersonnel() {
		return noteInfoPersonnel;
	}

	public void setNoteInfoPersonnel(String noteInfoPersonnel) {
		this.noteInfoPersonnel = noteInfoPersonnel;
	}

	/**
	 * @return le idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDg2 le idDg2 à affecter
	 
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	public List<Long> getPromotionId(){
		List<Long> promotionsId = new ArrayList<>();
		if (promotions != null) {
			for(Promotion p : promotions) {
				promotionsId.add(p.getId());
			}
		}
		return promotionsId;
}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateFin.hashCode());
		result = prime * result + ((dateFin== null) ? 0 : dateFin.hashCode());
		result = prime * result + ((promotions == null) ? 0 : promotions.hashCode());
		result = prime * result + ((formateurs== null) ? 0 : formateurs.hashCode());
		result = prime * result + (int) (idDg2 ^ (idDg2 >>> 32));
		return result;}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervention other = (Intervention) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (idDg2 != other.idDg2)
			return false;
		if (promotions == null) {
			if (other.promotions != null)
				return false;
		} else if (!promotions.stream().map(p ->p.getId()).collect(Collectors.toList()).equals(other.promotions.stream().map(p ->p.getId()).collect(Collectors.toList())))
			return false;
		if (formateurs == null) {
			if (other.formateurs != null)
				return false;
		} else if (!formateurs.stream().map(f ->f.getId()).collect(Collectors.toList()).equals(other.formateurs.stream().map(f ->f.getId()).collect(Collectors.toList())))
			return false;
		
		return true;
	}


}
