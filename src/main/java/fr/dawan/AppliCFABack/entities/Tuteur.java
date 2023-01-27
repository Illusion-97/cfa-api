package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Tuteur extends BaseEntity implements Serializable{
    
    @ManyToOne
    private Utilisateur utilisateur;
    
    @ManyToOne
    private Etudiant etudiant;
    
    @ManyToOne
    private Promotion promotion;
    
    public Tuteur() {
        super();
    }
    
    public Tuteur(Utilisateur utilisateur, Etudiant etudiant, Promotion promotion) {
        super();
        this.utilisateur = utilisateur;
        this.etudiant = etudiant;
        this.promotion = promotion;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    
    
    
    

}
