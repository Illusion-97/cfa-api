package fr.dawan.AppliCFABack.dto.customdtos;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import fr.dawan.AppliCFABack.entities.Positionnement;


public class GrillePositionnementDto {

	private LocalDate dateDebut;

	private LocalDate dateFin;

	private String module;

	private String formateur;

	private String objectifPedagogiques;

	private Map<String, Positionnement> etudiantsPositionnements;

	/**
	 * @return le dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut le dateDebut à affecter
	 
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return le dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin le dateFin à affecter
	 
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return le module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module le module à affecter
	 
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return le formateurs
	 */
	public String getFormateur() {
		return formateur;
	}

	/**
	 * @param formateurs le formateurs à affecter
	 
	 */
	public void setFormateur(String formateur) {
		this.formateur = formateur;
	}

	/**
	 * @return le objectifPedagogiques
	 */
	public String getObjectifPedagogiques() {
		return objectifPedagogiques;
	}

	/**
	 * @param objectifPedagogiques le objectifPedagogiques à affecter
	 
	 */
	public void setObjectifPedagogiques(String objectifPedagogiques) {
		this.objectifPedagogiques = objectifPedagogiques;
	}

	/**
	 * @return le etudiantsPositionnements
	 */
	public Map<String, Positionnement> getEtudiantsPositionnements() {
		return etudiantsPositionnements;
	}

	/**
	 * @param etudiantsPositionnements le etudiantsPositionnements à affecter
	 
	 */
	public void setEtudiantsPositionnements(Map<String, Positionnement> etudiantsPositionnements) {
		this.etudiantsPositionnements = etudiantsPositionnements;
	}



	
}
