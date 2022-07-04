package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.entities.ActiviteType;

public class EvalByBlocDto {

    private ActiviteType activiteType;
    private double moyenne;
    private double moyennePromo;

    public ActiviteType getActiviteType() {
        return activiteType;
    }

    public void setActiviteType(ActiviteType activiteType) {
        this.activiteType = activiteType;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public double getMoyennePromo() {
        return moyennePromo;
    }

    public void setMoyennePromo(double moyennePromo) {
        this.moyennePromo = moyennePromo;
    }
}
