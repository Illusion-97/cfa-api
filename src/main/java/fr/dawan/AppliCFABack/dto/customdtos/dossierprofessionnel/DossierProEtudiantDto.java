package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel;

import fr.dawan.AppliCFABack.dto.AnnexeDto;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.dto.FacultatifDto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DossierProEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private CursusDossierProDto cursusDto;
    private List<ExperienceProfessionnelleDto> experienceProfessionnelleDtos;
    private List<AnnexeDto> annexeDtos;
    private List<FacultatifDto> facultatifDto;
    private int version;

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

 

    public CursusDossierProDto getCursusDto() {
		return cursusDto;
	}

	public void setCursusDto(CursusDossierProDto cursusDto) {
		this.cursusDto = cursusDto;
	}

	public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
	

	public List<ExperienceProfessionnelleDto> getExperienceProfessionnelleDtos() {
		return experienceProfessionnelleDtos;
	}

	public void setExperienceProfessionnelleDtos(List<ExperienceProfessionnelleDto> experienceProfessionnelleDtos) {
		this.experienceProfessionnelleDtos = experienceProfessionnelleDtos;
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