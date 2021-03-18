package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

@Entity
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String Nom;
	
	@Column(nullable = false, length = 255)
	private String Description;
	
	@OneToMany(mappedBy = "programmePromotion", cascade = CascadeType.ALL)
	private List<ProgrammePromotion> programmePromotion;
	
	@Version
	private int version;

	public Promotion() {
		super();
	}

	public Promotion(long id, String nom, String description, List<ProgrammePromotion> programmePromotion) {
		super();
		this.id = id;
		Nom = nom;
		Description = description;
		this.programmePromotion = programmePromotion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public List<ProgrammePromotion> getProgrammePromotion() {
		return programmePromotion;
	}

	public void setProgrammePromotion(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotion = programmePromotion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
