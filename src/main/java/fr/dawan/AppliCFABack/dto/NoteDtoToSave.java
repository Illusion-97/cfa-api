package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Note.Satisfaction;

public class NoteDtoToSave {
	

		private long id;
		private double noteObtenue;
		private Satisfaction satisfaction;
		private long etudiantNoteId;
		private long examenId;
		

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
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

}
