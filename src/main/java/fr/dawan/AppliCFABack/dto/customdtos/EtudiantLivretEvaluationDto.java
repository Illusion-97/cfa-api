package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.entities.Note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class EtudiantLivretEvaluationDto extends BaseEntityDto implements Serializable {
    private List<String> promotions;
    private String examen;
    private List<String> competences = new ArrayList<>();
    private List<Note.Satisfaction> satisfactions = new ArrayList<>();
    private List<String> observations = new ArrayList<>();

    public EtudiantLivretEvaluationDto() {
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
