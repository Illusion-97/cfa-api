package fr.dawan.AppliCFABack.dto.customdtos.dossierprofessionnel.pdf;

import java.io.Serializable;
import java.util.Set;

public class PdfActiviteDto implements Serializable {

    private long id;
    private String libelle;
    private Set<PdfCompetenceDto> pdfCompetenceDtoSet;

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

    public Set<PdfCompetenceDto> getPdfCompetenceDtoSet() {
        return pdfCompetenceDtoSet;
    }

    public void setPdfCompetenceDtoSet(Set<PdfCompetenceDto> pdfCompetenceDtoSet) {
        this.pdfCompetenceDtoSet = pdfCompetenceDtoSet;
    }
}
