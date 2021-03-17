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
	private List<AbsenceDto> abscences;
	
	@XmlElement
	private EntrepriseDto entreprise;
	
	@XmlElement
	private List<NoteDto> notes;
	
	@XmlElement
	private List<PromotionDto> promotions;
	
	@XmlElement
	private List<GroupeDto> groupes;

	public EtudiantDto() {
		super();
	}
	
	public EtudiantDto(long id, PersonneDto personne, List<AbsenceDto> abscences, EntrepriseDto entreprise,
			List<NoteDto> notes, List<PromotionDto> promotions, List<GroupeDto> groupes) {
		super();
		this.id = id;
		this.personne = personne;
		this.abscences = abscences;
		this.entreprise = entreprise;
		this.notes = notes;
		this.promotions = promotions;
		this.groupes = groupes;
	}

	public long getIdEtudiant() {
		return id;
	}

	public void setIdEtudiant(long id) {
		this.id = id;
	}
	
	public PersonneDto getPersonneDto() {
		return personne;
	}

	public void setPersonneDto(PersonneDto personne) {
		this.personne = personne;
	}

	public List<AbsenceDto> getAbsenceDto() {
		return abscences;
	}

	public void setAbsenceDto(List<AbsenceDto> abscences) {
		this.abscences = abscences;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entreprise;
	}

	public void setEntrepriseDto(EntrepriseDto entreprise) {
		this.entreprise = entreprise;
	}

	public List<NoteDto> getNoteDto() {
		return notes;
	}

	public void setNoteDto(List<NoteDto> notes) {
		this.notes = notes;
	}

	public List<PromotionDto> getPromotionDto() {
		return promotions;
	}

	public void setPromotionDto(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public List<GroupeDto> getGroupeDto() {
		return groupes;
	}

	public void setGroupeDto(List<GroupeDto> groupes) {
		this.groupes = groupes;
	}

	
}
