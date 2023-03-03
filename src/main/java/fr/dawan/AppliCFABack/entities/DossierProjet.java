package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@SuppressWarnings("serial")
@Entity
public class DossierProjet extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@ManyToOne
	private Projet projet;
	
	@ManyToOne
    private Etudiant etudiant;

	@OneToMany(mappedBy = "dossierProjet", cascade = CascadeType.ALL)
    private List<AnnexeDossierProjet> annexeDossierProjets;


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public List<AnnexeDossierProjet> getAnnexeDossierProjets() {
        return annexeDossierProjets;
    }

    public void setAnnexeDossierProjets(List<AnnexeDossierProjet> annexeDossierProjets) {
        this.annexeDossierProjets = annexeDossierProjets;
    }

	
	

}
