package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Promotion;

@SuppressWarnings("serial")
public class SoutenanceDto extends BaseEntityDto implements Serializable {
	
	private Etudiant etudiant;
	
	private LocalDateTime dateTime;

	public SoutenanceDto() {
		super();
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
}
