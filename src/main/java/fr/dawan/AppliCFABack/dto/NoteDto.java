package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import fr.dawan.AppliCFABack.entities.Note.Satisfaction;

@SuppressWarnings("serial")
public class NoteDto extends BaseEntityDto implements Serializable {
	private double noteObtenue;
	private Satisfaction satisfaction;
	private long etudiantNoteId;
	private long examenId;
	private String etudiantNoteUtilisateurNom;
	private String etudiantNoteUtilisateurPrenom;

	public NoteDto() {
		super();
	}

	public NoteDto(double noteObtenue, long etudiantNoteId) {
		super();
		this.noteObtenue = noteObtenue;
		this.etudiantNoteId = etudiantNoteId;
	}

	public String getEtudiantNoteUtilisateurNom() {
		return etudiantNoteUtilisateurNom;
	}

	public void setEtudiantNoteUtilisateurNom(String etudiantNoteUtilisateurNom) {
		this.etudiantNoteUtilisateurNom = etudiantNoteUtilisateurNom;
	}

	public String getEtudiantNoteUtilisateurPrenom() {
		return etudiantNoteUtilisateurPrenom;
	}

	public void setEtudiantNoteUtilisateurPrenom(String etudiantNoteUtilisateurPrenom) {
		this.etudiantNoteUtilisateurPrenom = etudiantNoteUtilisateurPrenom;
	}

	public double getNoteObtenue() {
		return noteObtenue;
	}

	public void setNoteObtenue(double noteObtenue) {
		this.noteObtenue = noteObtenue;
	}

	public Satisfaction getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Satisfaction satisfaction) {
		this.satisfaction = satisfaction;
	}

	public long getEtudiantNoteId() {
		return etudiantNoteId;
	}

	public void setEtudiantNoteId(long etudiantNoteId) {
		this.etudiantNoteId = etudiantNoteId;
	}

	public long getExamenId() {
		return examenId;
	}

	public void setExamenId(long examenId) {
		this.examenId = examenId;
	}

//	public int getNoteObtenu() {
//		return noteObtenu;
//	}
//
//	public void setNoteObtenu(int noteObtenu) {
//		this.noteObtenu = noteObtenu;
//	}
//
//	public String getObservations() {
//		return observations;
//	}
//
//	public void setObservations(String observations) {
//		this.observations = observations;
//	}
//
//	public EtudiantDto getEtudiantDto() {
//		return etudiantDto;
//	}
//
//	public void setEtudiantDto(EtudiantDto etudiantDto) {
//		this.etudiantDto = etudiantDto;
//	}
//
//	public PassageExamenDto getExamenDto() {
//		return examenDto;
//	}
//
//	public void setExamenDto(PassageExamenDto examenDto) {
//		this.examenDto = examenDto;
//	}
//
//	public DevoirDto getDevoirDto() {
//		return devoirDto;
//	}
//
//	public void setDevoirDto(DevoirDto devoirDto) {
//		this.devoirDto = devoirDto;
//	}

}
