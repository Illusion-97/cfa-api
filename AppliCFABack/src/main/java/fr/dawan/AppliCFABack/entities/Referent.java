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
	private List<Promotion> promotion;
	
	@Version
	private int version;

	public Referent() {
		super();
	}

	public Referent(long id, Personne personne, List<Promotion> promotion) {
		super();
		this.id = id;
		this.personne = personne;
		this.promotion = promotion;
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

	public List<Promotion> getPromotion() {
		return promotion;
	}

	public void setPromotion(List<Promotion> promotion) {
		this.promotion = promotion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
