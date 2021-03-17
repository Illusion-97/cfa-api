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
	private String Nom;
	@XmlElement
	private String Description;
	@XmlElement
	private List<PromotionDto> promotions;

	public ProgrammePromotionDto() {
		super();
	}

	public ProgrammePromotionDto(long id, String nom, String description, List<PromotionDto> promotions) {
		super();
		this.id = id;
		Nom = nom;
		Description = description;
		this.promotions = promotions;
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

	public List<PromotionDto> getPromotion() {
		return promotions;
	}

	public void setPromotion(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}


}
