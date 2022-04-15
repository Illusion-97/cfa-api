package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class AbsenceDto implements Serializable{
	private long id;
	private int version; 
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String justificatif;
	private EtudiantDto etudiantDto;
	private long interventionId;
	private String typeAbsence;

	public AbsenceDto() {
		super();
	}

	
	public String getTypeAbsence() {
		return typeAbsence;
	}


	public void setTypeAbsence(String typeAbsence) {
		this.typeAbsence = typeAbsence;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getInterventionId() {
		return interventionId;
	}

	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}
	
}
