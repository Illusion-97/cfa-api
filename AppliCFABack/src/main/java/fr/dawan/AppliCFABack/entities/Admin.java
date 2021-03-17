package fr.dawan.AppliCFABack.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAdmin;
	
	@OneToOne
	private Personne personne;
	
	@Version
	private int version;

	
	public Admin() {
		super();
	}

	public Admin(long idAdmin, Personne personne) {
		super();
		this.idAdmin = idAdmin;
		this.personne = personne;
	}

	public long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
		
}
