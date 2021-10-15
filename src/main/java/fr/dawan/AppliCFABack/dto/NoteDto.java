package fr.dawan.AppliCFABack.dto;

public class NoteDto {
	private long id;
	private int noteObtenu;
	private String observations;
	private EtudiantDto etudiantDto;
	private PassageExamenDto examenDto;
	private DevoirDto devoirDto;

	public NoteDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNoteObtenu() {
		return noteObtenu;
	}

	public void setNoteObtenu(int noteObtenu) {
		this.noteObtenu = noteObtenu;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

	public PassageExamenDto getExamenDto() {
		return examenDto;
	}

	public void setExamenDto(PassageExamenDto examenDto) {
		this.examenDto = examenDto;
	}

	public DevoirDto getDevoirDto() {
		return devoirDto;
	}

	public void setDevoirDto(DevoirDto devoirDto) {
		this.devoirDto = devoirDto;
	}

}
