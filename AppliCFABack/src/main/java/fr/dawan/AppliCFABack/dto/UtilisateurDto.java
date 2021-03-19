package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class UtilisateurDto {
	private long id;
	private String login;
	private String password;
	private String prenom;
	private String nom;
	private AdresseDto adresse;
	private EntrepriseDto entrepriseDto;
	private List<UtilisateurRoleDto> rolesDto;

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

	public AdresseDto getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseDto adresse) {
		this.adresse = adresse;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

	public List<UtilisateurRoleDto> getRolesDto() {
		return rolesDto;
	}

	public void setRolesDto(List<UtilisateurRoleDto> rolesDto) {
		this.rolesDto = rolesDto;
	}

}
