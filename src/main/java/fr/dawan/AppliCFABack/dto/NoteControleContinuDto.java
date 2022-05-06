package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;

public class NoteControleContinuDto {
    private long id;
    private double noteObtenue;
    private String examen;
    private String promotion;
    private LocalDate date;
    private long etudiantId;

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

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        this.etudiantId = etudiantId;
    }
}
