package fr.dawan.AppliCFABack.entities;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

import javax.persistence.*;


/**
 * @author William P. Rémy C.
 * @see CompetenceProfessionnelle
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
@Entity
public class ExperienceProfessionnelle extends BaseEntity implements Serializable {

    /**
     * champs permettant de remplir la partie "tâches réalisées" d'une compétence dans le dossier professionnel
     */
    @Column(columnDefinition = "TEXT")
    private String tacheRealisee;

    /**
     * champs permettant de remplir la partie "moyens utilisés" d'une compétence dans le dossier professionnel
     */
    @Column(columnDefinition = "TEXT")
    private String moyenUtilise;

    /**
     * Champs permettant de remplir la partie "collaborateur" d'une compétence dans le dossie professionnel
     */
    private String collaborateur;

    /**
     * champs permettant de remplir la partie "contexte" d'une compétence dans le dossier professionnel
     */
    @Column(columnDefinition = "TEXT")
    private String contexte;

    /**
     * champs permettant de remplir la partie "informations complémentaires" d'une compétence dans le dossier professionnel
     */
    @Column(columnDefinition = "TEXT")
    private String information;

    @ManyToOne
    private DossierProfessionnel dossierProfessionnel;

//    @OneToMany(mappedBy = "experienceProfessionnelle", cascade = CascadeType.ALL)
//    private Set<CompetenceExperienceEtudiant> competenceExperienceEtudiants;

    @ManyToOne
    private CompetenceProfessionnelle competenceProfessionnelle;

    @ManyToOne Etudiant etudiant;

    /**
     * @return la tâche réalisée
     */
    public String getTacheRealisee() {
        return tacheRealisee;
    }

    /**
     * @param tacheRealisee la tâche à affecter
     */
    public void setTacheRealisee(String tacheRealisee) {
        this.tacheRealisee = tacheRealisee;
    }

    public String getMoyenUtilise() {
        return moyenUtilise;
    }

    public void setMoyenUtilise(String moyenUtilise) {
        this.moyenUtilise = moyenUtilise;
    }

    /**
     * @return le collaborateur
     */
    public String getCollaborateur() {
        return collaborateur;
    }

    /**
     * @param collaborateur Le collaborateur à affecter
     */
    public void setCollaborateur(String collaborateur) {
        this.collaborateur = collaborateur;
    }

    /**
     * @return le contexte
     */
    public String getContexte() {
        return contexte;
    }

    /**
     * @param contexte le contexte à affecter
     */
    public void setContexte(String contexte) {
        this.contexte = contexte;
    }

    /**
     * @return l'information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information l'information à affecter
     */
    public void setInformation(String information) {
        this.information = information;
    }

    public DossierProfessionnel getDossierProfessionnel() {
        return dossierProfessionnel;
    }

    public void setDossierProfessionnel(DossierProfessionnel dossierProfessionnel) {
        this.dossierProfessionnel = dossierProfessionnel;
    }

//    public Set<CompetenceExperienceEtudiant> getCompetenceExperienceEtudiants() {
//        return competenceExperienceEtudiants;
//    }
//
//    public void setCompetenceExperienceEtudiants(Set<CompetenceExperienceEtudiant> competenceExperienceEtudiants) {
//        this.competenceExperienceEtudiants = competenceExperienceEtudiants;
//    }

        public CompetenceProfessionnelle getCompetenceProfessionnelle() {
        return competenceProfessionnelle;
    }

    public void setCompetenceProfessionnelle(CompetenceProfessionnelle competenceProfessionnelle) {
        this.competenceProfessionnelle = competenceProfessionnelle;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
