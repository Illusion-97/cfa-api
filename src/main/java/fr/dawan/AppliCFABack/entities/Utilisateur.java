package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String login;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, length = 255)
	private String prenom;

	@Column(nullable = false, length = 255)
	private String nom;

	@Column(nullable = false, length = 255)
	private String civilite;

	private LocalDate dateDeNaissance;

	@Column(nullable = false, length = 255)
	private String telephone;

	@ManyToOne(cascade = CascadeType.ALL)
	private Adresse adresse;

	@ManyToMany
	private List<UtilisateurRole> roles;

//	@ManyToOne
//	private Entreprise entreprise;

	@OneToOne(cascade = CascadeType.ALL)
	private Etudiant etudiant;
	@OneToOne
	private Formateur formateur;
	@OneToOne
	private CEF cef;
	@OneToOne
	private MaitreApprentissage maitreApprentissage;

	public Utilisateur() {
		super();
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

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<UtilisateurRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UtilisateurRole> roles) {
		this.roles = roles;
	}

//	public Entreprise getEntreprise() {
//		return entreprise;
//	}
//
//	public void setEntreprise(Entreprise entreprise) {
//		this.entreprise = entreprise;
//	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public CEF getCef() {
		return cef;
	}

	public void setCef(CEF cef) {
		this.cef = cef;
	}

	public MaitreApprentissage getMaitreApprentissage() {
		return maitreApprentissage;
	}

	public void setMaitreApprentissage(MaitreApprentissage maitreApprentissage) {
		this.maitreApprentissage = maitreApprentissage;
	}

}
