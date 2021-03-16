package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "programmePromotion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProgrammePromotionDto implements Serializable {

	private long id;

	private String Nom;

	private String Description;

	private List<PromotionDto> promotions;

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

	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

}
