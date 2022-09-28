package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
public class Entreprise extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String raisonSociale;
	@Max(14)
	@Min(14)
	@Column(nullable = false, length = 14)
	private String siret;

	@Max(5)
	@Min(5)
	@Column(nullable = false, length = 5)
	private String naf;

	@Column
	private int effectifTotal;

	@Enumerated(EnumType.STRING)
	@Column
	private EmployeurType employeurType;

	@ManyToOne
	private Adresse adresseSiege;

	public enum EmployeurType {
		PRIVE, PUBLIC
	}

	public Entreprise() {
		super();
	}

	public Entreprise(String raisonSociale, Adresse adresseSiege) {
		super();
		this.raisonSociale = raisonSociale;
		this.adresseSiege = adresseSiege;
	}

	/**
	 * @return le employeurType
	 */
	public EmployeurType getEmployeurType() {
		return employeurType;
	}

	/**
	 * @param employeurType le employeurType à affecter
	 * 
	 */
	public void setEmployeurType(EmployeurType employeurType) {
		this.employeurType = employeurType;
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

	/**
	 * @return le effectifTotal
	 */
	public int getEffectifTotal() {
		return effectifTotal;
	}

	/**
	 * @param effectifTotal le effectifTotal à affecter
	 
	 */
	public void setEffectifTotal(int effectifTotal) {
		this.effectifTotal = effectifTotal;
	}



}
