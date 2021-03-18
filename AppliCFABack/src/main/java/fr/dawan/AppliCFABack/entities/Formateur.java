package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Formateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Personne personne;
	
	@ManyToMany
	private List<ProgrammeCours> programmeCours;
	
	@Version
	private int version;

	public Formateur() {
		super();
	}

	public Formateur(long id, Personne personne, List<ProgrammeCours> programmeCours) {
		super();
		this.id = id;
		this.personne = personne;
		this.programmeCours = programmeCours;
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

	public List<ProgrammeCours> getProgrammeCours() {
		return programmeCours;
	}

	public void setProgrammeCours(List<ProgrammeCours> programmeCours) {
		this.programmeCours = programmeCours;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
