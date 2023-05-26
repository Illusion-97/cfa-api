package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
public class Conge extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private LocalDate dateDebut;

	@Column(nullable = false)
	private LocalDate dateFin;

	private String motif;

	private TypeConge type;

	private StatusConge status;

	private String justificatif;

	@ManyToOne
	private Utilisateur utilisateur;

	public Conge() {
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

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
