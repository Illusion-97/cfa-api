package fr.dawan.AppliCFABack.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Remuneration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateDebut;
	
	@Column(nullable = true, length = 255)
	private LocalDate dateFin;
	
	@Column(nullable = true, length = 255)
	private String pourcentage;
	
	@Column(nullable = true, length = 255)
	private String smicOuSmc;

	public Remuneration() {
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

	public String getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(String pourcentage) {
		this.pourcentage = pourcentage;
	}

	public String getSmicOuSmc() {
		return smicOuSmc;
	}

	public void setSmicOuSmc(String smicOuSmc) {
		this.smicOuSmc = smicOuSmc;
	}

}
