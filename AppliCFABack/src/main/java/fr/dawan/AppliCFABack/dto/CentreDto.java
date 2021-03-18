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
	private CEFDto CEF;
	@XmlElement
	private List<ProgrammePromotionDto> programmePromotion;

	public CentreDto() {
		super();
	}

	public CentreDto(long id, AdresseDto adresseDto, CEFDto CEFDto, List<ProgrammePromotionDto> ProgrammePromotionDto) {
		super();
		this.id = id;
		this.adresse = adresseDto;
		this.CEF = CEFDto;
		this.programmePromotion = ProgrammePromotionDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AdresseDto getAdresseDto() {
		return adresse;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresse = adresseDto;
	}

	public CEFDto getCEFDto() {
		return CEF;
	}

	public void setCEFDto(CEFDto cEFDto) {
		CEF = cEFDto;
	}

	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotion;
	}

	public void setProgrammePromotionDto(List<ProgrammePromotionDto> ProgrammePromotionDto) {
		this.programmePromotion = ProgrammePromotionDto;
	}

	
}
