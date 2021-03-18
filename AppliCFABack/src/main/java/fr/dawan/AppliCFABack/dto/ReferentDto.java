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
	private List<ProgrammePromotionDto> programmePromotions;

	public ReferentDto() {
		super();
	}

	public ReferentDto(long id, PersonneDto personneDto, List<ProgrammePromotionDto> ProgrammePromotionDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.personneDto = personne;
		this.promotion = promotion;
=======
		this.personne = personneDto;
		this.programmePromotions = ProgrammePromotionDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
<<<<<<< HEAD
		return personneDto;
=======
		return personne;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setPersonneDto(PersonneDto personne) {
		this.personneDto = personne;
=======
	public void setPersonneDto(PersonneDto personneDto) {
		this.personne = personneDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotions;
	}

	public void setProgrammePromotionDto(List<ProgrammePromotionDto> ProgrammePromotionDto) {
		this.programmePromotions = ProgrammePromotionDto;
	}

}
