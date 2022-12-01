package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.entities.Note;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class EtudiantLivretEvaluationDto extends BaseEntityDto implements Serializable {

    private List<String> promotions;
    private List<String> observations = new ArrayList<>();

    public List<String> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<String> promotions) {
        this.promotions = promotions;
    }

    public List<String> getObservations() {
        return observations;
    }

    public void setObservations(List<String> observations) {
        this.observations = observations;
    }
}
