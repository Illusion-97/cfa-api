package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "centre")
@XmlAccessorType(XmlAccessType.FIELD)
public class CentreDto implements Serializable {

	private long id;

	private AdresseDto adresse;

	private CEFDto cef;

	private List<PromotionDto> promotions;

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

	public CEFDto getCef() {
		return cef;
	}

	public void setCef(CEFDto cef) {
		this.cef = cef;
	}

	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}
	
	
}
