package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

public class AccueilEtudiantDto implements Serializable {

    private String nom;
    private String prenom;
    private String login;
    private String telephone;
    private String ville;
    private String promotion;
    private List<List<String>> projets;
    private List<String> groupes;
    private String managerNom;
    private String managerPrenom;
    private String managerRole;
    private String managerEmail;
    private Stream<Object> membreEtudiantDtos;
//    private List<Stream<String>> membresPrenom;
//    private List<Stream<String>> membresNom;
//    private List<Stream<Stream<String>>> membresRole;

    private Stream<PlanningEtudiantDto> prochainCours;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public List<List<String>> getProjets() {
        return projets;
    }

    public void setProjets(List<List<String>> projets) {
        this.projets = projets;
    }

    public List<String> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<String> groupes) {
        this.groupes = groupes;
    }

    public String getManagerNom() {
        return managerNom;
    }

    public void setManagerNom(String managerNom) {
        this.managerNom = managerNom;
    }

    public String getManagerPrenom() {
        return managerPrenom;
    }

    public void setManagerPrenom(String managerPrenom) {
        this.managerPrenom = managerPrenom;
    }

    public String getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(String managerRole) {
        this.managerRole = managerRole;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

//    public List<Stream<String>> getMembresPrenom() {
//        return membresPrenom;
//    }
//
//    public void setMembresPrenom(List<Stream<String>> membresPrenom) {
//        this.membresPrenom = membresPrenom;
//    }
//
//    public List<Stream<String>> getMembresNom() {
//        return membresNom;
//    }
//
//    public void setMembresNom(List<Stream<String>> membresNom) {
//        this.membresNom = membresNom;
//    }
//
//    public List<Stream<Stream<String>>> getMembresRole() {
//        return membresRole;
//    }
//
//    public void setMembresRole(List<Stream<Stream<String>>> membresRole) {
//        this.membresRole = membresRole;
//    }


    public Stream<PlanningEtudiantDto> getProchainCours() {
        return prochainCours;
    }

    public void setProchainCours(Stream<PlanningEtudiantDto> prochainCours) {
        this.prochainCours = prochainCours;
    }

    public Stream<Object> getMembreEtudiantDtos() {
        return membreEtudiantDtos;
    }

    public void setMembreEtudiantDtos(Stream<Object> membreEtudiantDtos) {
        this.membreEtudiantDtos = membreEtudiantDtos;
    }
}

