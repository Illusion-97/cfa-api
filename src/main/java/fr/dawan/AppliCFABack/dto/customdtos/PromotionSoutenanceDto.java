package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.CEFDto;
import fr.dawan.AppliCFABack.dto.CentreFormationDto;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class PromotionSoutenanceDto {

    private String type;
    private long nbParticipants;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private CEFDto cefDto;
    private CentreFormationDto centreFormationDto;
    private String centreFormationAdresseVille;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(long nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public CEFDto getCefDto() {
        return cefDto;
    }

    public void setCefDto(CEFDto cefDto) {
        this.cefDto = cefDto;
    }

    public CentreFormationDto getCentreFormationDto() {
        return centreFormationDto;
    }

    public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
        this.centreFormationDto = centreFormationDto;
    }

    public String getCentreFormationAdresseVille() {
        return centreFormationAdresseVille;
    }

    public void setCentreFormationAdresseVille(String centreFormationAdresseVille) {
        this.centreFormationAdresseVille = centreFormationAdresseVille;
    }

}
