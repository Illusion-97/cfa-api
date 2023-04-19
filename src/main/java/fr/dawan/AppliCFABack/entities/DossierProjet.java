package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "dossier_projet_competence_professionnelles",
	    joinColumns = @JoinColumn(name = "dossier_projet_id"),
	    inverseJoinColumns = @JoinColumn(name = "competence_professionnelle_id"))
	private List<CompetenceProfessionnelle> competenceProfessionnelles;
	
	public List<Long> getCompetenceProfessionnelleDtos() {
		List<Long> competenceProfessionnelleIds = new ArrayList<>();
		for(CompetenceProfessionnelle cp : competenceProfessionnelles ) {
			competenceProfessionnelleIds.add(cp.getId());
		}
		return competenceProfessionnelleIds;
	}

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

	public List<CompetenceProfessionnelle> getCompetenceProfessionnelles() {
		return competenceProfessionnelles;
	}

	public void setCompetenceProfessionnelles(List<CompetenceProfessionnelle> competenceProfessionnelles) {
		this.competenceProfessionnelles = competenceProfessionnelles;
	}
	
	

}
