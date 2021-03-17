package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "personne")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonneDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String login;
	@XmlElement
	private String password;
	@XmlElement
	private String prenom;
	@XmlElement
	private String nom;
	@XmlElement
	private AdresseDto adresse;
	@XmlElement
	private FormateurDto formateur;
	@XmlElement
	private EtudiantDto etudiant;
	@XmlElement
	private CEFDto cef;
	@XmlElement
	private ReferentDto referent;
	@XmlElement
	private AdminDto admin;
	@XmlElement
	private List<ProjetDto> projets;

	public PersonneDto() {
		super();
	}
	
	public PersonneDto(long id, String login, String password, String prenom, String nom, AdresseDto adresse,
			FormateurDto formateur, EtudiantDto etudiant, CEFDto cef, ReferentDto referent, AdminDto admin,
			List<ProjetDto> projets) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.formateur = formateur;
		this.etudiant = etudiant;
		this.cef = cef;
		this.referent = referent;
		this.admin = admin;
		this.projets = projets;
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

	public FormateurDto getFormateur() {
		return formateur;
	}

	public void setFormateur(FormateurDto formateur) {
		this.formateur = formateur;
	}

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}

	public CEFDto getCEF() {
		return cef;
	}

	public void setCEF(CEFDto cef) {
		this.cef = cef;
	}

	public ReferentDto getReferent() {
		return referent;
	}

	public void setReferent(ReferentDto referent) {
		this.referent = referent;
	}

	public AdminDto getAdmin() {
		return admin;
	}

	public void setAdmin(AdminDto admin) {
		this.admin = admin;
	}

	public List<ProjetDto> getProjet() {
		return projets;
	}

	public void setProjet(List<ProjetDto> projets) {
		this.projets = projets;
	}

}
