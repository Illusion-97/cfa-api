package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.MaitreApprentissage;

public class ContratDto {
	
	private long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private MaitreApprentissage maitreApprentissage;
	
	public ContratDto() {
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

	public MaitreApprentissage getMaitreApprentissage() {
		return maitreApprentissage;
	}

	public void setMaitreApprentissage(MaitreApprentissage maitreApprentissage) {
		this.maitreApprentissage = maitreApprentissage;
	}

}
