package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.Entreprise;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-utilisateur Entity
 */
@SuppressWarnings("serial")
public class UtilisateurDto extends BaseEntityDto implements Serializable {

	private String login;
	private String password;
	private String prenom;
	private String nom;
	protected String civilite;
	private LocalDate dateDeNaissance;
	private String telephone;
	private String telephoneFixe;
	private AdresseDto adresseDto;
	private Entreprise entrepriseDto;
	private List<UtilisateurRoleDto> rolesDto;
	private EtudiantDto etudiantDto;
	private FormateurDto formateurDto;
	private TuteurDto tuteurDto;
	private CEFDto cefDto;
	private long idDg2;
	private long centreFormationId;
	private boolean externalAccount;
	private boolean active;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(boolean externalAccount) {
		this.externalAccount = externalAccount;
	}

	public String getFullName() {
		return getNom() + " " + getPrenom();
	}

	public UtilisateurDto() {
		super();
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the civilite
	 */
	public String getCivilite() {
		return civilite;
	}

	/**
	 * @param civilite the civilite to set
	 */
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	/**
	 * @return the dateDeNaissance
	 */
	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * @param dateDeNaissance the dateDeNaissance to set
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	/**
	 * @return the adresseDto
	 */
	public AdresseDto getAdresseDto() {
		return adresseDto;
	}

	/**
	 * @param adresseDto the adresseDto to set
	 */
	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}

	/**
	 * @return the rolesDto
	 */
	public List<UtilisateurRoleDto> getRolesDto() {
		return rolesDto;
	}

	/**
	 * @param rolesDto the rolesDto to set
	 */
	public void setRolesDto(List<UtilisateurRoleDto> rolesDto) {
		this.rolesDto = rolesDto;
	}

	/**
	 * @return the etudiantDto
	 */
	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	/**
	 * @param etudiantDto the etudiantDto to set
	 */
	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

	/**
	 * @return the formateurDto
	 */
	public FormateurDto getFormateurDto() {
		return formateurDto;
	}

	/**
	 * @param formateurDto the formateurDto to set
	 */
	public void setFormateurDto(FormateurDto formateurDto) {
		this.formateurDto = formateurDto;
	}

	public TuteurDto getTuteurDto() {
		return tuteurDto;
	}

	public void setTuteurDto(TuteurDto tuteurDto) {
		this.tuteurDto = tuteurDto;
	}
	
	/**
	 * @return the cefDto
	 */
	public CEFDto getCefDto() {
		return cefDto;
	}

	/**
	 * @param cefDto the cefDto to set
	 */
	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
	}

	public void setEntrepriseDto(Entreprise entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public Entreprise getEntrepriseDto() {
		return entrepriseDto;
	}

	/**
	 * @return le idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDg2 le idDg2 à affecter
	 * 
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	/**
	 * @return le centreFormationId
	 */
	public long getCentreFormationId() {
		return centreFormationId;
	}

	/**
	 * @param centreFormationId le centreFormationId à affecter
	 * 
	 */
	public void setCentreFormationId(long centreFormationId) {
		this.centreFormationId = centreFormationId;
	}

	@Override
	public String toString() {
		return "UtilisateurDto{" + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\''
				+ ", prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", civilite='" + civilite + '\''
				+ ", dateDeNaissance=" + dateDeNaissance + ", telephone='" + telephone + '\'' + ", adresseDto="
				+ adresseDto + ", entrepriseDto=" + entrepriseDto + ", rolesDto=" + rolesDto + ", etudiantDto="
				+ etudiantDto + ", formateurDto=" + formateurDto + ", cefDto=" + cefDto + '}';
	}

	public UtilisateurDto(String login, String password, String prenom, String nom, String civilite,
			LocalDate dateDeNaissance, String telephone, String telephoneFixe, AdresseDto adresseDto,
			Entreprise entrepriseDto, List<UtilisateurRoleDto> rolesDto, EtudiantDto etudiantDto,
			FormateurDto formateurDto, TuteurDto tuteurDto, CEFDto cefDto, long idDg2, long centreFormationId,
			boolean externalAccount, boolean active) {
		super();
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.civilite = civilite;
		this.dateDeNaissance = dateDeNaissance;
		this.telephone = telephone;
		this.telephoneFixe = telephoneFixe;
		this.adresseDto = adresseDto;
		this.entrepriseDto = entrepriseDto;
		this.rolesDto = rolesDto;
		this.etudiantDto = etudiantDto;
		this.formateurDto = formateurDto;
		this.tuteurDto = tuteurDto;
		this.cefDto = cefDto;
		this.idDg2 = idDg2;
		this.centreFormationId = centreFormationId;
		this.externalAccount = externalAccount;
		this.active = active;
	}

	public UtilisateurDto(long id, int version) {
		super(id, version);
		// TODO Auto-generated constructor stub
	}

}
