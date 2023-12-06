package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProjetDossierProjetDto implements Serializable {

    private long id;
    private String nom;
    private int version;

    public static ProjetDossierProjetDto create(long id,String nom, int version){
        ProjetDossierProjetDto projetDto = new ProjetDossierProjetDto();
        projetDto.setId(id);
        projetDto.setNom(nom);
        projetDto.setVersion(version);
        return projetDto;
    }
    public ProjetDossierProjetDto() {
        super();
    }
    
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
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
    
    
    
    

}
