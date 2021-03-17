package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class CEF {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Personne personne;
	
	@OneToOne
	private Centre centre;
	
	@Version
	private int version;

	public CEF() {
		super();
	}

	public CEF(long id, Personne personne, Centre centre) {
		super();
		this.id = id;
		this.personne = personne;
		this.centre = centre;
	}

	public long getIdCEF() {
		return id;
	}

	public void setIdCEF(long id) {
		this.id = id;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
