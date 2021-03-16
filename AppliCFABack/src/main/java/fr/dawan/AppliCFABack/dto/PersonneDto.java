package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "personne")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonneDto implements Serializable {

	private long id;

	private String login;

	private String password;

	private String prenom;

	private String nom;

	private AdresseDto adresse;

	private FormateurDto formateur;

	private EtudiantDto etudiant;

	private CEFDto cef;

	private ReferentDto referent;

	private AdminDto admin;

	private List<ProjetDto> projets;

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

	public CEFDto getCef() {
		return cef;
	}

	public void setCef(CEFDto cef) {
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

	public List<ProjetDto> getProjets() {
		return projets;
	}

	public void setProjets(List<ProjetDto> projets) {
		this.projets = projets;
	}

	
}
