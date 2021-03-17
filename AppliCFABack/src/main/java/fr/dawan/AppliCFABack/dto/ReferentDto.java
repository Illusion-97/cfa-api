package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "referent")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReferentDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private PersonneDto personneDto;
	@XmlElement
	private List<PromotionDto> promotionDto;

	public ReferentDto() {
		super();
	}

	public ReferentDto(long id, PersonneDto personneDto, List<PromotionDto> promotionDto) {
		super();
		this.id = id;
		this.personneDto = personneDto;
		this.promotionDto = promotionDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
		return personneDto;
	}

	public void setPersonneDto(PersonneDto personneDto) {
		this.personneDto = personneDto;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotionDto;
	}

	public void setPromotionDto(List<PromotionDto> promotionDto) {
		this.promotionDto = promotionDto;
	}

}
