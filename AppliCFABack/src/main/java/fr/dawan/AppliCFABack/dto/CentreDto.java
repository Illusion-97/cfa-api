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
	private AdresseDto adresseDto;
	@XmlElement
	private CEFDto cefDto;
	@XmlElement
	private List<PromotionDto> promotionsDto;

	public CentreDto() {
		super();
	}

	public CentreDto(long id, AdresseDto adresse, CEFDto cef, List<PromotionDto> promotions) {
		super();
		this.id = id;
		this.adresseDto = adresse;
		this.cefDto = cef;
		this.promotionsDto = promotions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdresseDto getAdresseDto() {
		return adresseDto;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}

	public CEFDto getCefDto() {
		return cefDto;
	}

	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
	}

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	
}
