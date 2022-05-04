package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-promotion Entity
 */
@SuppressWarnings("serial")
public class PromotionDto extends BaseEntityDto implements Serializable {

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

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the cefDto
	 */
	public CEFDto getCefDto() {
		return cefDto;
	}

	/**
	 * @param cefDto the cefDto to set
	 */
	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
	}

	/**
	 * @return the etudiantsDto
	 */
	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	/**
	 * @param etudiantsDto the etudiantsDto to set
	 */
	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiantsDto = etudiantsDto;
	}

	/**
	 * @return the interventionsDto
	 */
	public List<InterventionDto> getInterventionsDto() {
		return interventionsDto;
	}

	/**
	 * @param interventionsDto the interventionsDto to set
	 */
	public void setInterventionsDto(List<InterventionDto> interventionsDto) {
		this.interventionsDto = interventionsDto;
	}

	/**
	 * @return the examensDto
	 */
	public Set<ExamenDto> getExamensDto() {
		return examensDto;
	}

	/**
	 * @param examensDto the examensDto to set
	 */
	public void setExamensDto(Set<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

	/**
	 * @return the centreFormationDto
	 */
	public CentreFormationDto getCentreFormationDto() {
		return centreFormationDto;
	}

	/**
	 * @param centreFormationDto the centreFormationDto to set
	 */
	public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
		this.centreFormationDto = centreFormationDto;
	}

	/**
	 * @return the referentPedagogiqueDto
	 */
	public UtilisateurDto getReferentPedagogiqueDto() {
		return referentPedagogiqueDto;
	}

	/**
	 * @param referentPedagogiqueDto the referentPedagogiqueDto to set
	 */
	public void setReferentPedagogiqueDto(UtilisateurDto referentPedagogiqueDto) {
		this.referentPedagogiqueDto = referentPedagogiqueDto;
	}

	/**
	 * @return the cursusDto
	 */
	public CursusDto getCursusDto() {
		return cursusDto;
	}

	/**
	 * @param cursusDto the cursusDto to set
	 */
	public void setCursusDto(CursusDto cursusDto) {
		this.cursusDto = cursusDto;
	}

}
