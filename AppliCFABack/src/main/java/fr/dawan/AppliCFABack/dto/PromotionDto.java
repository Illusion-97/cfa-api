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
	private List<ProgrammePromotionDto> programmePromotionsDto;

	public PromotionDto() {
		super();
	}

	public PromotionDto(long id, String nom, String description, List<ProgrammePromotionDto> programmePromotionsDto) {
		super();
		this.id = id;
		Nom = nom;
		Description = description;
		this.programmePromotionsDto = programmePromotionsDto;
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

	public List<ProgrammePromotionDto> getProgrammePromotionsDto() {
		return programmePromotionsDto;
	}

	public void setProgrammePromotionsDto(List<ProgrammePromotionDto> programmePromotionsDto) {
		this.programmePromotionsDto = programmePromotionsDto;
	}

}
