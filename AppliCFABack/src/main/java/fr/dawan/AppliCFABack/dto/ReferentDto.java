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

	public long getIdReferentDto() {
		return id;
	}

	public void setIdReferentDto(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
		return personne;
	}

	public void setPersonneDto(PersonneDto personne) {
		this.personne = personne;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotion;
	}

	public void setPromotionDto(List<PromotionDto> promotion) {
		this.promotion = promotion;
	}

}
