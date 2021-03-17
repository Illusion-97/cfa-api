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

	public long getIdCentreDto() {
		return id;
	}

	public void setIdCentreDto(long id) {
		this.id = id;
	}

	public AdresseDto getAdresseDto() {
		return adresse;
	}

	public void setAdresseDto(AdresseDto adresse) {
		this.adresse = adresse;
	}

	public CEFDto getCEFDto() {
		return cef;
	}

	public void setCEFDto(CEFDto cef) {
		this.cef = cef;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotions;
	}

	public void setPromotionDto(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	
}
