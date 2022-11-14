//package fr.dawan.AppliCFABack.entities;
//
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import java.io.Serializable;
//
//@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"competence_professionnelle_id", "etudiant_id", "experience_professionnelle_id"}))
//public class CompetenceExperienceEtudiant extends BaseEntity implements Serializable {
//
//    @ManyToOne
//    private CompetenceProfessionnelle competenceProfessionnelle;
//
//    @ManyToOne
//    private Etudiant etudiant;
//
//    @ManyToOne
//    private ExperienceProfessionnelle experienceProfessionnelle;
//
//    public CompetenceProfessionnelle getCompetenceProfessionnelle() {
//        return competenceProfessionnelle;
//    }
//
//    public void setCompetenceProfessionnelle(CompetenceProfessionnelle competenceProfessionnelle) {
//        this.competenceProfessionnelle = competenceProfessionnelle;
//    }
//
//    public Etudiant getEtudiant() {
//        return etudiant;
//    }
//
//    public void setEtudiant(Etudiant etudiant) {
//        this.etudiant = etudiant;
//    }
//
//    public ExperienceProfessionnelle getExperienceProfessionnelle() {
//        return experienceProfessionnelle;
//    }
//
//    public void setExperienceProfessionnelle(ExperienceProfessionnelle experienceProfessionnelle) {
//        this.experienceProfessionnelle = experienceProfessionnelle;
//    }
//}
