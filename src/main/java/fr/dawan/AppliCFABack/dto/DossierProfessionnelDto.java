package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
	
	private String fileImport;
		


	public DossierProfessionnelDto(long id,String nom, int version, CursusDto cursusDto,
			List<ExperienceProfessionnelleDto> experienceProfessionnelleDtos, EtudiantDto etudiantDto,
			List<AnnexeDto> annexeDtos, List<FacultatifDto> facultatifDto, String fileImport) {
		super();
		this.id = id;
		this.version = version;
		this.nom = nom;
		this.cursusDto = cursusDto;
		this.experienceProfessionnelleDtos = experienceProfessionnelleDtos;
		this.etudiantDto = etudiantDto;
		this.annexeDtos = annexeDtos;
		this.facultatifDto = facultatifDto;
		this.fileImport = fileImport;
	}

	public DossierProfessionnelDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getFileImport() {
		return fileImport;
	}

	public void setFileImport(String fileImport) {
		this.fileImport = fileImport;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    DossierProfessionnelDto that = (DossierProfessionnelDto) o;
	    return Objects.equals(id, that.id) &&
	           Objects.equals(nom, that.nom) &&
	           Objects.equals(version, that.version);
	}

	@Override
	public int hashCode() {
	    return Objects.hash(id, nom, version);
	}
}
