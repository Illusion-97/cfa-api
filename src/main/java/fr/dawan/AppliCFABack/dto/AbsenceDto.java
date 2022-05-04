package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class AbsenceDto extends BaseEntityDto implements Serializable{
	
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String justificatif;
	private EtudiantDto etudiantDto;
	private long interventionId;
	private String typeAbsence;

	public AbsenceDto() {
		super();
	}
	
	public AbsenceDto(long id, int version, LocalDate dateDebut, LocalDate dateFin, String justificatif,
			EtudiantDto etudiantDto, long interventionId, String typeAbsence) {
		super();
		this.id = id;
		this.version = version;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justificatif = justificatif;
		this.etudiantDto = etudiantDto;
		this.interventionId = interventionId;
		this.typeAbsence = typeAbsence;
	}
	
	public AbsenceDto(long id, int version, LocalDate dateDebut, LocalDate dateFin, String justificatif,
			long interventionId, String typeAbsence) {
		super();
		this.id = id;
		this.version = version;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justificatif = justificatif;
		this.interventionId = interventionId;
		this.typeAbsence = typeAbsence;
	}


	public String getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(String typeAbsence) {
		this.typeAbsence = typeAbsence;
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

	public long getInterventionId() {
		return interventionId;
	}

	public void setInterventionId(long interventionId) {
		this.interventionId = interventionId;
	}
	
}
