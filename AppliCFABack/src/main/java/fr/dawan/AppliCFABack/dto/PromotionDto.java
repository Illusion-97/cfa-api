package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "promotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class PromotionDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String Nom;
	@XmlElement
	private String Description;
	@XmlElement
<<<<<<< HEAD
	private CentreDto centreDto;
	@XmlElement
	private ProgrammePromotionDto programmePromotionDto;
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private List<EtudiantDto> etudiantsDto;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCoursDto;
=======
	private List<ProgrammePromotionDto> programmePromotions;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack

	public PromotionDto() {
		super();
	}

	public PromotionDto(long id, String nom, String description, List<ProgrammePromotionDto> programmePromotionDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centreDto = centre;
		this.programmePromotionDto = programmePromotionDto;
		this.referentDto = referentDto;
		this.etudiantsDto = etudiantsDto;
		this.programmeCoursDto = programmeCoursDto;
=======
		Nom = nom;
		Description = description;
		this.programmePromotions = programmePromotionDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

<<<<<<< HEAD
	public CentreDto getCentreDto() {
		return centreDto;
=======
	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotions;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setCentreDto(CentreDto centre) {
		this.centreDto = centre;
=======
	public void setProgrammePromotionDto(List<ProgrammePromotionDto> programmePromotionDto) {
		this.programmePromotions = programmePromotionDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public ProgrammePromotionDto getProgrammePromotionDto() {
		return programmePromotionDto;
	}
=======
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack

<<<<<<< HEAD
	public void setProgrammePromotionDto(ProgrammePromotionDto programmePromotion) {
		this.programmePromotionDto = programmePromotion;
	}

	public ReferentDto getReferentDto() {
		return referentDto;
	}

	public void setReferentDto(ReferentDto referent) {
		this.referentDto = referent;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiantsDto;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiants) {
		this.etudiantsDto = etudiants;
	}

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCoursDto;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCoursDto = programmeCoursDto;
	}
	
	
=======
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
}
