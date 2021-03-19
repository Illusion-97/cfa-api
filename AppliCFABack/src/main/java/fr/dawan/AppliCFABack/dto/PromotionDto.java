package fr.dawan.AppliCFABack.dto;

<<<<<<< HEAD
public class PromotionDto {
=======
import java.util.Date;
import java.util.List;

public class PromotionDto {
	private long id;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private UtilisateurDto cef;
	private List<EtudiantDto> etudiants;
	private CentreFormationDto centreFormation;
	private UtilisateurDto referentPedagogique;
	private CursusDto cursus;

	public PromotionDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public UtilisateurDto getCef() {
		return cef;
	}

	public void setCef(UtilisateurDto cef) {
		this.cef = cef;
	}

	public List<EtudiantDto> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<EtudiantDto> etudiants) {
		this.etudiants = etudiants;
	}

	public CentreFormationDto getCentreFormation() {
		return centreFormation;
	}

	public void setCentreFormation(CentreFormationDto centreFormation) {
		this.centreFormation = centreFormation;
	}

	public UtilisateurDto getReferentPedagogique() {
		return referentPedagogique;
	}

	public void setReferentPedagogique(UtilisateurDto referentPedagogique) {
		this.referentPedagogique = referentPedagogique;
	}

	public CursusDto getCursus() {
		return cursus;
	}

	public void setCursus(CursusDto cursus) {
		this.cursus = cursus;
	}
>>>>>>> refs/heads/main

}
