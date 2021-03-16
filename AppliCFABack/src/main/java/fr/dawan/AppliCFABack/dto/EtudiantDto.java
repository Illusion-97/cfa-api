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

	public List<AbsenceDto> getAbscences() {
		return abscences;
	}

	public void setAbscences(List<AbsenceDto> abscences) {
		this.abscences = abscences;
	}

	public EntrepriseDto getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(EntrepriseDto entreprise) {
		this.entreprise = entreprise;
	}

	public List<NoteDto> getNotes() {
		return notes;
	}

	public void setNotes(List<NoteDto> notes) {
		this.notes = notes;
	}

	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public List<GroupeDto> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<GroupeDto> groupes) {
		this.groupes = groupes;
	}
	
	
}
