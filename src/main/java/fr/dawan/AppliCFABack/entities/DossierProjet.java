package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.util.List;


@SuppressWarnings("serial")
@Entity
public class DossierProjet extends BaseEntity {

	@Column(nullable = false, length = 255)
	private String nom;

	@ManyToOne
	private Projet projet;
	
	@ManyToOne
    private Etudiant etudiant;
	@Column(nullable = true, name = "import", length = 100)
	private String dossierImport;
	@ElementCollection
    private List<String> annexeDossierProjets;
	
	@ElementCollection
    private List<String> infoDossierProjets;

	@ElementCollection
	private List<String> contenuDossierProjets;

	@ElementCollection
	private List<String> resumeDossierProjets;

	@ManyToMany
	private List<CompetenceProfessionnelle> competenceProfessionnelles;

	public String getDossierImport() {
		return dossierImport;
	}

	public void setDossierImport(String dossierImport) {
		this.dossierImport = dossierImport;
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

    public List<String> getAnnexeDossierProjets() {
        return annexeDossierProjets;
    }

	public void setAnnexeDossierProjets(List<String> annexeDossierProjets) {
		this.annexeDossierProjets = annexeDossierProjets;
	}

	public List<CompetenceProfessionnelle> getCompetenceProfessionnelles() {
		return competenceProfessionnelles;
	}

	public void setCompetenceProfessionnelles(List<CompetenceProfessionnelle> competenceProfessionnelles) {
		this.competenceProfessionnelles = competenceProfessionnelles;
	}

	public List<String> getInfoDossierProjets() {
		return infoDossierProjets;
	}

	public void setInfoDossierProjets(List<String> infoDossierProjets) {
		this.infoDossierProjets = infoDossierProjets;
	}

	public List<String> getContenuDossierProjets() {
		return contenuDossierProjets;
	}

	public void setContenuDossierProjets(List<String> contenuDossierProjets) {
		this.contenuDossierProjets = contenuDossierProjets;
	}

	public List<String> getResumeDossierProjets() {
		return resumeDossierProjets;
	}

	public void setResumeDossierProjets(List<String> resumeDossierProjets) {
		this.resumeDossierProjets = resumeDossierProjets;
	}
}
