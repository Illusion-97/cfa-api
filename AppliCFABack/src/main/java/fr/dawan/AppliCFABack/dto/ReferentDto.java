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
	private PersonneDto personne;
	@XmlElement
	private List<PromotionDto> promotion;

	public ReferentDto() {
		super();
	}

	public ReferentDto(long id, PersonneDto personne, List<PromotionDto> promotion) {
		super();
		this.id = id;
		this.personne = personne;
		this.promotion = promotion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonne() {
		return personne;
	}

	public void setPersonne(PersonneDto personne) {
		this.personne = personne;
	}

	public List<PromotionDto> getPromotion() {
		return promotion;
	}

	public void setPromotion(List<PromotionDto> promotion) {
		this.promotion = promotion;
	}

}
