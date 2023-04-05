package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;

public class GetProjetDossierProjetDto implements Serializable {

    private long id;
    private String nom;
    private DossierProjetEtudiantDto dossierProjet;
    private int version;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public DossierProjetEtudiantDto getDossierProjet() {
        return dossierProjet;
    }
    public void setDossierProjet(DossierProjetEtudiantDto dossierProjet) {
        this.dossierProjet = dossierProjet;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

}
