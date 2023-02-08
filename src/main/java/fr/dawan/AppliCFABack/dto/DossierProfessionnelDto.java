package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Dossier profesionnel Entity
 */
@SuppressWarnings("serial")
public class DossierProfessionnelDto extends BaseEntityDto implements Serializable {

	private String nom;

	private CursusDto cursusDto;

	private List<ExperienceProfessionnelleDto> experienceProfessionnelleDtos;

	private EtudiantDto etudiantDto;

	private List<AnnexeDto> annexeDtos;
	
	private List<FacultatifDto> facultatifDto;

	public DossierProfessionnelDto() {
		super();
	}

	public DossierProfessionnelDto(String nom) {
		this.nom = nom;
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

	public List<ExperienceProfessionnelleDto> getExperienceProfessionnelleDtos() {
		return experienceProfessionnelleDtos;
	}

	public void setExperienceProfessionnelleDtos(List<ExperienceProfessionnelleDto> experienceProfessionnelleDtos) {
		this.experienceProfessionnelleDtos = experienceProfessionnelleDtos;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

	public List<AnnexeDto> getAnnexeDtos() {
		return annexeDtos;
	}

	public void setAnnexeDtos(List<AnnexeDto> annexeDtos) {
		this.annexeDtos = annexeDtos;
	}

	public List<FacultatifDto> getFacultatifDto() {
		return facultatifDto;
	}

	public void setFacultatifDto(List<FacultatifDto> facultatifDto) {
		this.facultatifDto = facultatifDto;
	}
	
	
}
