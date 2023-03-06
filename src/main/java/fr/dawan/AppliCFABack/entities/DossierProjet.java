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
	
	@OneToMany(mappedBy = "dossierProjet", cascade = CascadeType.ALL)
    private List<InfoDossierProjet> infoDossierProjets;
	
	@OneToMany(mappedBy = "dossierProjet", cascade = CascadeType.ALL)
    private List<ContenuDossierProjet> contenuDossierProjets;
	
	@OneToMany(mappedBy = "dossierProjet", cascade = CascadeType.ALL)
    private List<ResumeDossierProjet> resumeDossierProjets;
	


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

	public List<InfoDossierProjet> getInfoDossierProjets() {
		return infoDossierProjets;
	}

	public void setInfoDossierProjets(List<InfoDossierProjet> infoDossierProjets) {
		this.infoDossierProjets = infoDossierProjets;
	}

	public List<ContenuDossierProjet> getContenuDossierProjets() {
		return contenuDossierProjets;
	}

	public void setContenuDossierProjets(List<ContenuDossierProjet> contenuDossierProjets) {
		this.contenuDossierProjets = contenuDossierProjets;
	}

	public List<ResumeDossierProjet> getResumeDossierProjets() {
		return resumeDossierProjets;
	}

	public void setResumeDossierProjets(List<ResumeDossierProjet> resumeDossierProjets) {
		this.resumeDossierProjets = resumeDossierProjets;
	}
	
	

}
