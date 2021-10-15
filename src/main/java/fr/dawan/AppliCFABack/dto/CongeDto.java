package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.StatusConge;
import fr.dawan.AppliCFABack.entities.TypeConge;

public class CongeDto {
	private long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private String motif;
	private TypeConge type;
	private UtilisateurDto utilisateurDto;
	private StatusConge status;
	private String justificatif;
	
	public CongeDto() {
		super();
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

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public TypeConge getType() {
		return type;
	}

	public void setType(TypeConge type) {
		this.type = type;
	}

	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	public StatusConge getStatus() {
		return status;
	}

	public void setStatus(StatusConge status) {
		this.status = status;
	}

	public String getJustificatif() {
		return justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}
	
}
