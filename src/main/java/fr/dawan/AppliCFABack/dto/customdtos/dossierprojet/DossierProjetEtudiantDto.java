package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.dto.AnnexeDossierProjetDto;
import fr.dawan.AppliCFABack.dto.ProjetDto;

@SuppressWarnings("serial")
public class DossierProjetEtudiantDto implements Serializable {

    private long id;
    private String nom;
    private ProjetDossierProjetDto projets;
    private List<AnnexeDossierProjetDto> annexeDossierProjets;
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
  
    public ProjetDossierProjetDto getProjets() {
        return projets;
    }
    public void setProjets(ProjetDossierProjetDto projets) {
        this.projets = projets;
    }
    public List<AnnexeDossierProjetDto> getAnnexeDossierProjets() {
        return annexeDossierProjets;
    }
    public void setAnnexeDossierProjets(List<AnnexeDossierProjetDto> annexeDossierProjets) {
        this.annexeDossierProjets = annexeDossierProjets;
    }
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    

   
}
