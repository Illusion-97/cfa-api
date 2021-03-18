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
public class Centre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Adresse adresse;
	
	@OneToOne
	private CEF cef;
	
	@OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
	private List<ProgrammePromotion> programmePromotions; 
		
	@Version
	private int version;

	public Centre() {
		super();
	}

	public Centre(long id, Adresse adresse, CEF cef, List<ProgrammePromotion> programmePromotion) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.cef = cef;
		this.programmePromotions = programmePromotion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public CEF getCef() {
		return cef;
	}

	public void setCef(CEF cef) {
		this.cef = cef;
	}

	public List<ProgrammePromotion> getProgrammePromotions() {
		return programmePromotions;
	}

	public void setProgrammePromotions(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotions = programmePromotion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
