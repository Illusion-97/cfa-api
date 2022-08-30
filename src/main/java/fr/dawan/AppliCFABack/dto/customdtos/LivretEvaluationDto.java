package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.entities.Note;

import java.util.ArrayList;
import java.util.List;

public class LivretEvaluationDto {
    private List<String> promotions;
    private String examen;
    private List<String> competences = new ArrayList<>();
    private List<Note.Satisfaction> satisfactions = new ArrayList<>();
    private List<String> observations = new ArrayList<>();

    public LivretEvaluationDto() {
    	super();
    }

    public List<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<String> promotions) {
        this.promotions = promotions;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public List<Note.Satisfaction> getSatisfactions() {
        return satisfactions;
    }

    public void setSatisfactions(List<Note.Satisfaction> satisfactions) {
        this.satisfactions = satisfactions;

    }

    public List<String> getObservations() {
        return observations;
    }

    public void setObservations(List<String> observations) {
        this.observations = observations;
    }
}
