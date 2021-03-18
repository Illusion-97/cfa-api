package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Referent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Personne personne;
	
	@OneToMany(mappedBy = "referent", cascade = CascadeType.ALL)
	private List<ProgrammePromotion> programmePromotions;
	
	@Version
	private int version;

	public Referent() {
		super();
	}

	public Referent(long id, Personne personne, List<ProgrammePromotion> programmePromotion) {
		super();
		this.id = id;
		this.personne = personne;
		this.programmePromotions = programmePromotion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<ProgrammePromotion> getProgrammePromotion() {
		return programmePromotions;
	}

	public void setProgrammePromotion(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotions = programmePromotion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
