package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.dto.AnnexeDossierProjetDto;
import fr.dawan.AppliCFABack.dto.ContenuDossierProjetDto;
import fr.dawan.AppliCFABack.dto.InfoDossierProjetDto;
import fr.dawan.AppliCFABack.dto.ResumeDossierProjetDto;

@SuppressWarnings("serial")
public class DossierProjetEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private String dossierImport;
    private ProjetDossierProjetDto projet;
    private List<AnnexeDossierProjetDto> annexeDossierProjets;
    private List<InfoDossierProjetDto> infoDossierProjets;
    private List<Long> competenceProfessionnelleIds;
    private List<ContenuDossierProjetDto> contenuDossierProjets;
    private List<ResumeDossierProjetDto> resumeDossierProjets;
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
    public List<AnnexeDossierProjetDto> getAnnexeDossierProjets() {
        return annexeDossierProjets;
    }
    public void setAnnexeDossierProjets(List<AnnexeDossierProjetDto> annexeDossierProjets) {
        this.annexeDossierProjets = annexeDossierProjets;
    }
    
    public List<InfoDossierProjetDto> getInfoDossierProjets() {
		return infoDossierProjets;
	}
	public void setInfoDossierProjets(List<InfoDossierProjetDto> infoDossierProjets) {
		this.infoDossierProjets = infoDossierProjets;
	}
	
	public List<ContenuDossierProjetDto> getContenuDossierProjets() {
		return contenuDossierProjets;
	}
	
	public void setContenuDossierProjets(List<ContenuDossierProjetDto> contenuDossierProjets) {
		this.contenuDossierProjets = contenuDossierProjets;
	}
	
	public List<ResumeDossierProjetDto> getResumeDossierProjets() {
		return resumeDossierProjets;
	}
	public void setResumeDossierProjets(List<ResumeDossierProjetDto> resumeDossierProjets) {
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
