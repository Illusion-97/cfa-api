package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

import fr.dawan.AppliCFABack.entities.MaitreApprentissage;

public class ContratDto {
	
	private long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private MaitreApprentissageDto maitreApprentissageDto;
	private EtudiantDto etudiantDto;
	
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

	public MaitreApprentissageDto getMaitreApprentissageDto() {
		return maitreApprentissageDto;
	}

	public void setMaitreApprentissageDto(MaitreApprentissageDto maitreApprentissageDto) {
		this.maitreApprentissageDto = maitreApprentissageDto;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}


}
