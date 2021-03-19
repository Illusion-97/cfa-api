package fr.dawan.AppliCFABack.dto;

import java.util.Date;
import java.util.List;

public class PromotionDto {
	private long id;
	private String nom;
	private Date dateDebut;
	private Date dateFin;
	private UtilisateurDto cefDto;
	private List<EtudiantDto> etudiantsDto;
	private CentreFormationDto centreFormationDto;
	private UtilisateurDto referentPedagogiqueDto;
	private CursusDto cursusDto;

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

	public UtilisateurDto getCefDto() {
		return cefDto;
	}

	public void setCefDto(UtilisateurDto cefDto) {
		this.cefDto = cefDto;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
	}

	public CentreFormationDto getCentreFormationDto() {
		return centreFormationDto;
	}

	public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
		this.centreFormationDto = centreFormationDto;
	}

	public UtilisateurDto getReferentPedagogiqueDto() {
		return referentPedagogiqueDto;
	}

	public void setReferentPedagogiqueDto(UtilisateurDto referentPedagogiqueDto) {
		this.referentPedagogiqueDto = referentPedagogiqueDto;
	}

	public CursusDto getCursusDto() {
		return cursusDto;
	}

	public void setCursusDto(CursusDto cursusDto) {
		this.cursusDto = cursusDto;
	}

}
