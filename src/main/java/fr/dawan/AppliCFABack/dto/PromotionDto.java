package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;



@SuppressWarnings("serial")
public class PromotionDto extends BaseEntityDto implements Serializable{

	private String nom;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private CEFDto cefDto;
	private List<EtudiantDto> etudiantsDto;
	private List<InterventionDto> interventionsDto;
	private Set<ExamenDto> examensDto;
	private CentreFormationDto centreFormationDto;
	private UtilisateurDto referentPedagogiqueDto;
	private CursusDto cursusDto;
	
	public PromotionDto() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public CEFDto getCefDto() {
		return cefDto;
	}

	public void setCefDto(CEFDto cefDto) {
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

	public List<InterventionDto> getInterventionsDto() {
		return interventionsDto;
	}

	public void setInterventionsDto(List<InterventionDto> interventionsDto) {
		this.interventionsDto = interventionsDto;
	}
	
	public Set<ExamenDto> getExamensDto() {
		return examensDto;
	}

	public void setExamensDto(Set<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

}
