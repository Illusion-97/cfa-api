package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DossierProjetEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private String dossierImport;
    private ProjetDossierProjetDto projet;
    private List<String> annexeDossierProjets;
    private List<String> infoDossierProjets;
    private List<Long> competenceProfessionnelleIds;
    private List<String> contenuDossierProjets;
    private List<String> resumeDossierProjets;
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

    public String getDossierImport() {
        return dossierImport;
    }

    public void setDossierImport(String dossierImport) {
        this.dossierImport = dossierImport;
    }

    public ProjetDossierProjetDto getProjet() {
        return projet;
    }
    public void setProjet(ProjetDossierProjetDto projet) {
        this.projet = projet;
    }
    public List<String> getAnnexeDossierProjets() {
        return annexeDossierProjets;
    }
    public void setAnnexeDossierProjets(List<String> annexeDossierProjets) {
        this.annexeDossierProjets = annexeDossierProjets;
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
	
	public int getVersion() {
        return version;
    }
	
    public void setVersion(int version) {
        this.version = ++version;
    }

	public List<Long> getCompetenceProfessionnelleIds() {
		return competenceProfessionnelleIds;
	}
	public void setCompetenceProfessionnelleIds(List<Long> competenceProfessionnelleIds) {
		this.competenceProfessionnelleIds = competenceProfessionnelleIds;
	}
    
    

   
}
