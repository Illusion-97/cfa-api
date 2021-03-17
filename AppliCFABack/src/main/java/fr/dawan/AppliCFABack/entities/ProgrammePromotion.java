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
public class ProgrammePromotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String Nom;
	
	@Column(nullable = false, length = 255)
	private String Description;
	
	@OneToMany(mappedBy = "programmePromotion", cascade = CascadeType.ALL)
	private List<Promotion> promotions;
	
	@Version
	private int version;

	public ProgrammePromotion() {
		super();
	}

	public ProgrammePromotion(long id, String nom, String description, List<Promotion> promotions) {
		super();
		this.id = id;
		Nom = nom;
		Description = description;
		this.promotions = promotions;
	}

	public long getIdProgrammePromotion() {
		return id;
	}

	public void setIdProgrammePromotion(long id) {
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

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
