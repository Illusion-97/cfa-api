package fr.dawan.AppliCFABack.dto;

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
	private String civilite;
	private LocalDate dateDeNaissance;
	private String telephone;
	private AdresseDto adresseDto;
	// private EntrepriseDto entrepriseDto;
	private List<UtilisateurRoleDto> rolesDto;
	private EtudiantDto etudiantDto;
	private FormateurDto formateurDto;
	private CEFDto cefDto;
	private MaitreApprentissageDto maitreApprentissageDto;

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

	/**
	 * @return the maitreApprentissageDto
	 */
	public MaitreApprentissageDto getMaitreApprentissageDto() {
		return maitreApprentissageDto;
	}

	/**
	 * @param maitreApprentissageDto the maitreApprentissageDto to set
	 */
	public void setMaitreApprentissageDto(MaitreApprentissageDto maitreApprentissageDto) {
		this.maitreApprentissageDto = maitreApprentissageDto;
	}

	@Override
	public String toString() {
		return "UtilisateurDto{" + "id=" + id + ", login='" + login + '\'' + ", password='" + password + '\''
				+ ", prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", civilite='" + civilite + '\''
				+ ", dateDeNaissance=" + dateDeNaissance + ", telephone='" + telephone + '\'' + ", adresseDto="
				+ adresseDto + ", rolesDto=" + rolesDto + ", etudiantDto=" + etudiantDto + ", formateurDto="
				+ formateurDto + ", cefDto=" + cefDto + ", maitreApprentissageDto=" + maitreApprentissageDto + '}';
	}
}
