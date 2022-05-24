package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.dawan.AppliCFABack.entities.Absence.TypeAbsence;

@SuppressWarnings("serial")
public class AbsenceDto extends BaseEntityDto implements Serializable{
	

	private LocalDateTime dateDebut;
	
	private LocalDateTime dateFin;


	private String justificatif;


	private long etudiantId;
	

	private long interventionId;
	

	private TypeAbsence typeAbsence;
	


	public AbsenceDto() {
		super();
	}



	/**
	 * @return le dateDebut
	 */
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}



	/**
	 * @param dateDebut le dateDebut à affecter
	 
	 */
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}



	/**
	 * @return le dateFin
	 */
	public LocalDateTime getDateFin() {
		return dateFin;
	}



	/**
	 * @param dateFin le dateFin à affecter
	 
	 */
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}



	/**
	 * @return le justificatif
	 */
	public String getJustificatif() {
		return justificatif;
	}



	/**
	 * @param justificatif le justificatif à affecter
	 
	 */
	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}



	/**
	 * @return le etudiantId
	 */
	public long getEtudiantId() {
		return etudiantId;
	}



	/**
	 * @param etudiantId le etudiantId à affecter
	 
	 */
	public void setEtudiantId(long etudiantId) {
		this.etudiantId = etudiantId;
	}



	/**
	 * @return le interventionId
	 */
	public long getInterventionId() {
		return interventionId;
	}



	/**
	 * @param interventionId le interventionId à affecter
	 
	 */
	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}



	/**
	 * @return le typeAbsence
	 */
	public TypeAbsence getTypeAbsence() {
		return typeAbsence;
	}



	/**
	 * @param typeAbsence le typeAbsence à affecter
	 
	 */
	public void setTypeAbsence(TypeAbsence typeAbsence) {
		this.typeAbsence = typeAbsence;
	}


	
}
