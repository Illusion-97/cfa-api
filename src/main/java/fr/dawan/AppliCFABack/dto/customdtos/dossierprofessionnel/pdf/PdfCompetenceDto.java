package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;

import java.io.Serializable;
import java.util.List;

public class PdfCompetenceDto implements Serializable {

    private long id;
    private String libelle;
    private byte numeroFiche;
    private List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList;

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

    public List<ExperienceProfessionnelleDto> getExperienceProfessionnelleDtoList() {
        return experienceProfessionnelleDtoList;
    }

    public void setExperienceProfessionnelleDtoList(List<ExperienceProfessionnelleDto> experienceProfessionnelleDtoList) {
        this.experienceProfessionnelleDtoList = experienceProfessionnelleDtoList;
    }

    public byte getNumeroFiche() {
        return numeroFiche;
    }

    public void setNumeroFiche(byte numeroFiche) {
        this.numeroFiche = numeroFiche;
    }
}
