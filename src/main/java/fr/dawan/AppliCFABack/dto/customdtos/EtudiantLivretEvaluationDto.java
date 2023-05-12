package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EtudiantLivretEvaluationDto implements Serializable {

    private long cursusId;
    private String cursus;
    private String observation;

    public String getCursus() {
        return cursus;
    }

    public void setCursus(String cursus) {
        this.cursus = cursus;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public long getCursusId() {
        return cursusId;
    }

    public void setCursusId(long cursusId) {
        this.cursusId = cursusId;
    }

}
