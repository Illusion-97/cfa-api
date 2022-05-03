package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class ContratDto extends BaseEntityDto implements Serializable{
	
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private MaitreApprentissageDto maitreApprentissageDto;
	private EtudiantDto etudiantDto;
	
	public ContratDto() {
		super();
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
