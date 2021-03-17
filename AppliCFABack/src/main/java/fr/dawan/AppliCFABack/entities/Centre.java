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
	private List<Promotion> promotions; 
		
	@Version
	private int version;

	public Centre() {
		super();
	}

	public Centre(long id, Adresse adresse, CEF cef, List<Promotion> promotions) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.cef = cef;
		this.promotions = promotions;
	}

	public long getIdCentre() {
		return id;
	}

	public void setIdCentre(long id) {
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
