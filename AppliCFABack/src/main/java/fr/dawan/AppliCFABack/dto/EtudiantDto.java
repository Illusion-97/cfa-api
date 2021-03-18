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
	private List<AbsenceDto> absenceDto;
	
	@XmlElement
	private EntrepriseDto entrepriseDto;
	
	@XmlElement
	private List<NoteDto> noteDto;
	
	@XmlElement
	private List<ProgrammePromotionDto> programmePromotionDto;
	
	@XmlElement
	private List<GroupeDto> groupeDto;

	public EtudiantDto() {
		super();
	}
	
	public EtudiantDto(long id, PersonneDto personneDto, List<AbsenceDto> absenceDto, EntrepriseDto entrepriseDto,
			List<NoteDto> noteDto, List<ProgrammePromotionDto> programmePromotionDto, List<GroupeDto> groupeDto) {
		super();
		this.id = id;
		this.personneDto = personneDto;
		this.absenceDto = absenceDto;
		this.entrepriseDto = entrepriseDto;
		this.noteDto = noteDto;
		this.programmePromotionDto = programmePromotionDto;
		this.groupeDto = groupeDto;
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

	public List<AbsenceDto> getAbsenceDto() {
		return absenceDto;
	}

	public void setAbsenceDto(List<AbsenceDto> absenceDto) {
		this.absenceDto = absenceDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public List<NoteDto> getNoteDto() {
		return noteDto;
	}

	public void setNoteDto(List<NoteDto> noteDto) {
		this.noteDto = noteDto;
	}

	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotionDto;
	}

	public void setProgrammePromotionDto(List<ProgrammePromotionDto> programmePromotionDto) {
		this.programmePromotionDto = programmePromotionDto;
	}

	public List<GroupeDto> getGroupeDto() {
		return groupeDto;
	}

	public void setGroupeDto(List<GroupeDto> groupeDto) {
		this.groupeDto = groupeDto;
	}

	
}
