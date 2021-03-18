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
<<<<<<< HEAD
	private CEFDto cefDto;
=======
	private CEFDto CEF;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
<<<<<<< HEAD
	private List<PromotionDto> promotionsDto;
=======
	private List<ProgrammePromotionDto> programmePromotion;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack

	public CentreDto() {
		super();
	}

	public CentreDto(long id, AdresseDto adresseDto, CEFDto CEFDto, List<ProgrammePromotionDto> ProgrammePromotionDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.adresseDto = adresse;
		this.cefDto = cef;
		this.promotionsDto = promotions;
=======
		this.adresse = adresseDto;
		this.CEF = CEFDto;
		this.programmePromotion = ProgrammePromotionDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdresseDto getAdresseDto() {
<<<<<<< HEAD
		return adresseDto;
=======
		return adresse;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setAdresseDto(AdresseDto adresseDto) {
<<<<<<< HEAD
		this.adresseDto = adresseDto;
=======
		this.adresse = adresseDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public CEFDto getCefDto() {
		return cefDto;
=======
	public CEFDto getCEFDto() {
		return CEF;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
=======
	public void setCEFDto(CEFDto cEFDto) {
		CEF = cEFDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
=======
	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotion;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
=======
	public void setProgrammePromotionDto(List<ProgrammePromotionDto> ProgrammePromotionDto) {
		this.programmePromotion = ProgrammePromotionDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	
}
