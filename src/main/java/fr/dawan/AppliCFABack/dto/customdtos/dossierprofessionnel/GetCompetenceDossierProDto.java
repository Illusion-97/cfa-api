package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel;

public class GetCompetenceDossierProDto {

    private long id;
    private String libelle;
    private byte numeroFiche;
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public byte getNumeroFiche() {
        return numeroFiche;
    }

    public void setNumeroFiche(byte numeroFiche) {
        this.numeroFiche = numeroFiche;
    }
}
