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
	
	@Column(nullable = false, length = 255)
	private String siret;
	
	@Column(nullable = false, length = 255)
	private String naf;
	
	@Column(nullable = false, length = 255)
	private String effectifTotal;
	
	@Column(nullable = false, length = 255)
	private String employeurType;
	
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

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getNaf() {
		return naf;
	}

	public void setNaf(String naf) {
		this.naf = naf;
	}

	public String getEffectifTotal() {
		return effectifTotal;
	}

	public void setEffectifTotal(String effectifTotal) {
		this.effectifTotal = effectifTotal;
	}

	public String getEmployeurType() {
		return employeurType;
	}

	public void setEmployeurType(String employeurType) {
		this.employeurType = employeurType;
	}
	
}
