package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "etudiant")
@XmlAccessorType(XmlAccessType.FIELD)
public class EtudiantDto implements Serializable {

	private long id;

	private PersonneDto personne;

	private List<AbsenceDto> abscences;

	private EntrepriseDto entreprise;

	private List<NoteDto> notes;

	private List<PromotionDto> promotions;

	private List<GroupeDto> groupes;

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
