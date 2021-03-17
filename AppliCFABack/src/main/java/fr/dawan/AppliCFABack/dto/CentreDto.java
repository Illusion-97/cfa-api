package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "centre")
@XmlAccessorType(XmlAccessType.FIELD)
public class CentreDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private AdresseDto adresse;
	@XmlElement
	private CEFDto cef;
	@XmlElement
	private List<PromotionDto> promotions;

	public CentreDto() {
		super();
	}

	public CentreDto(long id, AdresseDto adresse, CEFDto cef, List<PromotionDto> promotions) {
		super();
		this.id = id;
		this.adresse = adresse;
		this.cef = cef;
		this.promotions = promotions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdresseDto getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseDto adresse) {
		this.adresse = adresse;
	}

	public CEFDto getCEF() {
		return cef;
	}

	public void setCEF(CEFDto cef) {
		this.cef = cef;
	}

	public List<PromotionDto> getPromotion() {
		return promotions;
	}

	public void setPromotion(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	
}
