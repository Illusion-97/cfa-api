package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Examen;

public class NoteDto {
	private long id;
	private double noteObtenu;
	private boolean satifaction;
	private long etudiantNoteId;
	private long examenId;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getNoteObtenu() {
		return noteObtenu;
	}

	public void setNoteObtenu(double noteObtenu) {
		this.noteObtenu = noteObtenu;
	}

	public boolean isSatifaction() {
		return satifaction;
	}

	public void setSatifaction(boolean satifaction) {
		this.satifaction = satifaction;
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
