package fr.dawan.AppliCFABack.dto;

import java.time.LocalDate;
import java.util.List;

public class UtilisateurDto {
    private long id;
    private String login;
    private String password;
    private String prenom;
    private String nom;
    private String civilite;
    private LocalDate dateDeNaissance;
    private String telephone;
    private AdresseDto adresseDto;
    //	private EntrepriseDto entrepriseDto;
    private List<UtilisateurRoleDto> rolesDto;
    private EtudiantDto etudiantDto;
    private FormateurDto formateurDto;
    private CEFDto cefDto;
    private MaitreApprentissageDto maitreApprentissageDto;

    public UtilisateurDto() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AdresseDto getAdresseDto() {
        return adresseDto;
    }

    public void setAdresseDto(AdresseDto adresseDto) {
        this.adresseDto = adresseDto;
    }

//	public EntrepriseDto getEntrepriseDto() {
//		return entrepriseDto;
//	}
//
//	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
//		this.entrepriseDto = entrepriseDto;
//	}

    public List<UtilisateurRoleDto> getRolesDto() {
        return rolesDto;
    }

    public void setRolesDto(List<UtilisateurRoleDto> rolesDto) {
        this.rolesDto = rolesDto;
    }

    public EtudiantDto getEtudiantDto() {
        return etudiantDto;
    }

    public void setEtudiantDto(EtudiantDto etudiantDto) {
        this.etudiantDto = etudiantDto;
    }

    public FormateurDto getFormateurDto() {
        return formateurDto;
    }

    public void setFormateurDto(FormateurDto formateurDto) {
        this.formateurDto = formateurDto;
    }

    public CEFDto getCefDto() {
        return cefDto;
    }

    public void setCefDto(CEFDto cefDto) {
        this.cefDto = cefDto;
    }

    public MaitreApprentissageDto getMaitreApprentissageDto() {
        return maitreApprentissageDto;
    }

    public void setMaitreApprentissageDto(MaitreApprentissageDto maitreApprentissageDto) {
        this.maitreApprentissageDto = maitreApprentissageDto;
    }

    @Override
    public String toString() {
        return "UtilisateurDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", civilite='" + civilite + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", telephone='" + telephone + '\'' +
                ", adresseDto=" + adresseDto +
                ", rolesDto=" + rolesDto +
                ", etudiantDto=" + etudiantDto +
                ", formateurDto=" + formateurDto +
                ", cefDto=" + cefDto +
                ", maitreApprentissageDto=" + maitreApprentissageDto +
                '}';
    }
}
