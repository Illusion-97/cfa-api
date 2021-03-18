package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
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
	private String nom;
	@XmlElement
	private String description;
	@XmlElement
	private List<PromotionDto> promotionsDto;

	public ProgrammePromotionDto() {
		super();
	}

	public ProgrammePromotionDto(long id, String nom, String description, List<PromotionDto> promotions) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.promotionsDto = promotions;
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
		nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		description = description;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotionsDto;
	}

	public void setPromotionDto(List<PromotionDto> promotions) {
		this.promotionsDto = promotions;
	}


}
