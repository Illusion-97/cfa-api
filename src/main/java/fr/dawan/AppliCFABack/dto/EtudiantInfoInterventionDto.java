package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EtudiantInfoInterventionDto implements Serializable {

	private long idEtudiant;

	private String prenom;

	private String nom;

	private String nomCentreFormation;

	/**
	 * @return le idEtudiant
	 */
	public long getIdEtudiant() {
		return idEtudiant;
	}

	/**
	 * @param idEtudiant le idEtudiant à affecter
	 * 
	 */
	public void setIdEtudiant(long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	/**
	 * @return le prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom le prenom à affecter
	 * 
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom le nom à affecter
	 * 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return le nomCentreFormation
	 */
	public String getNomCentreFormation() {
		return nomCentreFormation;
	}

	/**
	 * @param nomCentreFormation le nomCentreFormation à affecter
	 * 
	 */
	public void setNomCentreFormation(String nomCentreFormation) {
		this.nomCentreFormation = nomCentreFormation;
	}

}
