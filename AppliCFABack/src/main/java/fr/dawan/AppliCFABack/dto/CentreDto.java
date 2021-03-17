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
	private CEFDto CEFDto;
	@XmlElement
	private List<PromotionDto> promotionDto;

	public CentreDto() {
		super();
	}

	public CentreDto(long id, AdresseDto adresseDto, CEFDto CEFDto, List<PromotionDto> promotionDto) {
		super();
		this.id = id;
		this.adresseDto = adresseDto;
		this.CEFDto = CEFDto;
		this.promotionDto = promotionDto;
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

	public CEFDto getCEFDto() {
		return CEFDto;
	}

	public void setCEFDto(CEFDto cEFDto) {
		CEFDto = cEFDto;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotionDto;
	}

	public void setPromotionDto(List<PromotionDto> promotionDto) {
		this.promotionDto = promotionDto;
	}

	
}
