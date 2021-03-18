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
	private PersonneDto personne;
	
	@XmlElement
	private List<AbsenceDto> absence;
	
	@XmlElement
	private EntrepriseDto entreprise;
	
	@XmlElement
	private List<NoteDto> notes;
	
	@XmlElement
	private List<ProgrammePromotionDto> programmePromotions;
	
	@XmlElement
	private List<GroupeDto> groupes;

	public EtudiantDto() {
		super();
	}
	
	public EtudiantDto(long id, PersonneDto personneDto, List<AbsenceDto> absenceDto, EntrepriseDto entrepriseDto,
			List<NoteDto> noteDto, List<ProgrammePromotionDto> programmePromotionDto, List<GroupeDto> groupeDto) {
		super();
		this.id = id;
		this.personne = personneDto;
		this.absence = absenceDto;
		this.entreprise = entrepriseDto;
		this.notes = noteDto;
		this.programmePromotions = programmePromotionDto;
		this.groupes = groupeDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public PersonneDto getPersonneDto() {
		return personne;
	}

	public void setPersonneDto(PersonneDto personneDto) {
		this.personne = personneDto;
	}

	public List<AbsenceDto> getAbsenceDto() {
		return absence;
	}

	public void setAbsenceDto(List<AbsenceDto> absenceDto) {
		this.absence = absenceDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entreprise;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entreprise = entrepriseDto;
	}

	public List<NoteDto> getNoteDto() {
		return notes;
	}

	public void setNoteDto(List<NoteDto> noteDto) {
		this.notes = noteDto;
	}

	public List<ProgrammePromotionDto> getProgrammePromotionDto() {
		return programmePromotions;
	}

	public void setProgrammePromotionDto(List<ProgrammePromotionDto> programmePromotionDto) {
		this.programmePromotions = programmePromotionDto;
	}

	public List<GroupeDto> getGroupeDto() {
		return groupes;
	}

	public void setGroupeDto(List<GroupeDto> groupeDto) {
		this.groupes = groupeDto;
	}

	
}
