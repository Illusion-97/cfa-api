package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Entreprise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String raisonSociale;

	@ManyToOne
	private Adresse adresseSiege;

	public Entreprise() {
		super();
	}

	public Entreprise(String raisonSociale, Adresse adresseSiege) {
		super();
		this.raisonSociale = raisonSociale;
		this.adresseSiege = adresseSiege;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public Adresse getAdresseSiege() {
		return adresseSiege;
	}

	public void setAdresseSiege(Adresse adresseSiege) {
		this.adresseSiege = adresseSiege;
	}

}
