package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Entreprise extends BaseEntity implements Serializable {

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
