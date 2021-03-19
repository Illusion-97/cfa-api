package fr.dawan.AppliCFABack.dto;

public class NoteDto {
	private long id;
	private int noteObtenu;
	private String observations;
	private EtudiantDto etudiant;
	private PassageExamenDto examen;
	private DevoirDto devoir;

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

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}

	public PassageExamenDto getExamen() {
		return examen;
	}

	public void setExamen(PassageExamenDto examen) {
		this.examen = examen;
	}

	public DevoirDto getDevoir() {
		return devoir;
	}

	public void setDevoir(DevoirDto devoir) {
		this.devoir = devoir;
	}

}
