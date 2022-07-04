package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;

public class DoubleDto implements Serializable {

    private double result;

    public DoubleDto() {
    }

    public DoubleDto(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
