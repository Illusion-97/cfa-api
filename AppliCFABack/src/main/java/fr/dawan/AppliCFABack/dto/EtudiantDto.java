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
<<<<<<< HEAD
	private PersonneDto personneDto;
	 
=======
	private PersonneDto personne;
	
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
<<<<<<< HEAD
	private List<AbsenceDto> absencesDto;
=======
	private List<AbsenceDto> absence;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	
	@XmlElement
	private EntrepriseDto entrepriseDto;
	
	@XmlElement
	private List<NoteDto> notesDto;
	
	@XmlElement
<<<<<<< HEAD
	private List<PromotionDto> promotionsDto;
=======
	private List<ProgrammePromotionDto> programmePromotions;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	
	@XmlElement
	private List<GroupeDto> groupesDto;

	public EtudiantDto() {
		super();
	}
	
	public EtudiantDto(long id, PersonneDto personneDto, List<AbsenceDto> absenceDto, EntrepriseDto entrepriseDto,
			List<NoteDto> noteDto, List<ProgrammePromotionDto> programmePromotionDto, List<GroupeDto> groupeDto) {
		super();
		this.id = id;
<<<<<<< HEAD
		this.personneDto = personne;
		this.absencesDto = absences;
		this.entrepriseDto = entreprise;
		this.notesDto = notes;
		this.promotionsDto = promotions;
		this.groupesDto = groupes;
=======
		this.personne = personneDto;
		this.absence = absenceDto;
		this.entreprise = entrepriseDto;
		this.notes = noteDto;
		this.programmePromotions = programmePromotionDto;
		this.groupes = groupeDto;
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
	
=======
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

>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	
}
