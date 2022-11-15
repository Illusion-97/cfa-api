package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ExperienceProfessionnelleDto implements Serializable {

    private long id;
    private String tacheRealisee;
    private String moyenUtilise;
    private String collaborateur;
    private String contexte;
    private String information;
    private int version;
    private long dossierProfessionnelId;
    private long competenceProfessionnelleId;
    private long etudiantId;


    public String getTacheRealisee() {
        return tacheRealisee;
    }

    public void setTacheRealisee(String tacheRealisee) {
        this.tacheRealisee = tacheRealisee;
    }

    public String getMoyenUtilise() {
        return moyenUtilise;
    }

    public void setMoyenUtilise(String moyenUtilise) {
        this.moyenUtilise = moyenUtilise;
    }

    public String getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(String collaborateur) {
        this.collaborateur = collaborateur;
    }

    public String getContexte() {
        return contexte;
    }

    public void setContexte(String contexte) {
        this.contexte = contexte;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getDossierProfessionnelId() {
        return dossierProfessionnelId;
    }

    public void setDossierProfessionnelId(long dossierProfessionnelId) {
        this.dossierProfessionnelId = dossierProfessionnelId;
    }

    public long getCompetenceProfessionnelleId() {
        return competenceProfessionnelleId;
    }

    public void setCompetenceProfessionnelleId(long competenceProfessionnelleId) {
        this.competenceProfessionnelleId = competenceProfessionnelleId;
    }

    public long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(long etudiantId) {
        this.etudiantId = etudiantId;
    }
}
