package fr.dawan.AppliCFABack.dto.customdtos;

import java.time.LocalDate;
import java.util.Set;

public class NoteControleContinuDto {
    private long id;
    private double noteObtenue;
    private String examen;
    private Set<String> promotions;
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

    public Set<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(Set<String> promotions) {
        this.promotions = promotions;
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
