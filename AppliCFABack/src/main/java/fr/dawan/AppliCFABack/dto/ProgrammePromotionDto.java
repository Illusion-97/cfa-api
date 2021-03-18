package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmePromotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammePromotionDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
<<<<<<< HEAD
	private String nom;
=======
	private Date dateDebut;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
<<<<<<< HEAD
	private String description;
=======
	private Date dateFin;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
<<<<<<< HEAD
	private List<PromotionDto> promotionsDto;
=======
	private CentreDto centre;
	@XmlElement
	private PromotionDto promotion;
	@XmlElement
	private ReferentDto referent;
	@XmlElement
	private List<EtudiantDto> etudiants;
	@XmlElement
	private List<ProgrammeCoursDto> programmeCours;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack

	public ProgrammePromotionDto() {
		super();
	}

	public ProgrammePromotionDto(long id, Date dateDebut, Date dateFin, CentreDto centre,
			PromotionDto promotionDto, ReferentDto referentDto, List<EtudiantDto> etudiantsDto,
			List<ProgrammeCoursDto> programmeCoursDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.nom = nom;
		this.description = description;
		this.promotionsDto = promotions;
=======
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.centre = centre;
		this.promotion = promotionDto;
		this.referent = referentDto;
		this.etudiants = etudiantsDto;
		this.programmeCours = programmeCoursDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

<<<<<<< HEAD
	public String getNom() {
		return nom;
=======
	public Date getDateDebut() {
		return dateDebut;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setNom(String nom) {
		nom = nom;
=======
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public String getDescription() {
		return description;
=======
	public Date getDateFin() {
		return dateFin;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setDescription(String description) {
		description = description;
=======
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public List<PromotionDto> getPromotionDto() {
		return promotionsDto;
=======
	public CentreDto getCentre() {
		return centre;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setPromotionDto(List<PromotionDto> promotions) {
		this.promotionsDto = promotions;
=======
	public void setCentre(CentreDto centre) {
		this.centre = centre;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public PromotionDto getPromotionDto() {
		return promotion;
	}

	public void setPromotionDto(PromotionDto promotionDto) {
		this.promotion = promotionDto;
	}

	public ReferentDto getReferentDto() {
		return referent;
	}

	public void setReferentDto(ReferentDto referentDto) {
		this.referent = referentDto;
	}

	public List<EtudiantDto> getEtudiantsDto() {
		return etudiants;
	}

	public void setEtudiantsDto(List<EtudiantDto> etudiantsDto) {
		this.etudiants = etudiantsDto;
	}

	public List<ProgrammeCoursDto> getProgrammeCoursDto() {
		return programmeCours;
	}

	public void setProgrammeCoursDto(List<ProgrammeCoursDto> programmeCoursDto) {
		this.programmeCours = programmeCoursDto;
	}
	
	
}
