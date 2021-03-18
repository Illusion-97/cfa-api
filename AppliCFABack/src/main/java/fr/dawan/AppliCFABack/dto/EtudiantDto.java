package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "etudiant")
@XmlAccessorType(XmlAccessType.FIELD)
public class EtudiantDto implements Serializable {
	
	@XmlElement
	private long id;
	
	@XmlElement
	private PersonneDto personneDto;
	
	@XmlElement
	private List<AbsenceDto> absencesDto;
	
	@XmlElement
	private EntrepriseDto entrepriseDto;
	
	@XmlElement
	private List<NoteDto> notesDto;
	
	@XmlElement
	private List<PromotionDto> promotionsDto;
	
	@XmlElement
	private List<GroupeDto> groupesDto;

	public EtudiantDto() {
		super();
	}

	public EtudiantDto(long id, PersonneDto personneDto, List<AbsenceDto> absencesDto, EntrepriseDto entrepriseDto,
			List<NoteDto> notesDto, List<PromotionDto> promotionsDto, List<GroupeDto> groupesDto) {
		super();
		this.id = id;
		this.personneDto = personneDto;
		this.absencesDto = absencesDto;
		this.entrepriseDto = entrepriseDto;
		this.notesDto = notesDto;
		this.promotionsDto = promotionsDto;
		this.groupesDto = groupesDto;
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

	public List<AbsenceDto> getAbsencesDto() {
		return absencesDto;
	}

	public void setAbsencesDto(List<AbsenceDto> absencesDto) {
		this.absencesDto = absencesDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public List<NoteDto> getNotesDto() {
		return notesDto;
	}

	public void setNotesDto(List<NoteDto> notesDto) {
		this.notesDto = notesDto;
	}

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public List<GroupeDto> getGroupesDto() {
		return groupesDto;
	}

	public void setGroupesDto(List<GroupeDto> groupesDto) {
		this.groupesDto = groupesDto;
	}

	
}
