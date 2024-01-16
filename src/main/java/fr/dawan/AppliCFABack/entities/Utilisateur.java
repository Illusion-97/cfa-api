package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur extends BaseEntity implements Serializable {
	
	@Column(nullable = false) 
	private long idDg2;

	@Column(nullable = false, length = 255)
	private String login;

	private String password;

	private String prenom;

	private String nom;

	private String civilite;

	private LocalDate dateDeNaissance;

	private String telephone;

	private String telephoneFixe;

	@ManyToOne(cascade = {CascadeType.PERSIST })
	private Adresse adresse;

	@ManyToMany
	private List<UtilisateurRole> roles;

	@ManyToOne
	private Entreprise entreprise;
	
	@ManyToOne
	private CentreFormation centreFormation;

	@OneToOne(mappedBy = "utilisateur")
	private Etudiant etudiant;
	
	@OneToOne(mappedBy = "utilisateur")
	private Formateur formateur;
	@OneToOne
	private CEF cef;
	
	@OneToOne
	private Tuteur tuteur;
	


	@OneToOne
	private Signature signature;

	/**
	 * External account = managed account by LDAP
	 */
	private boolean externalAccount;
	
	private boolean active;
	
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Utilisateur() {
		super();
	}
	
	public boolean isExternalAccount() {
		return externalAccount;
	}

	public void setExternalAccount(boolean externalAccount) {
		this.externalAccount = externalAccount;
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

	public String getRolesStr() {
		StringBuilder sb = new StringBuilder();
		for (UtilisateurRole ur : roles) {
			sb.append(ur.getIntitule()).append(" ");
		}
		return sb.toString();
	}

	
	public void setRoles(List<UtilisateurRole> roles) {
		this.roles = roles;
	}


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

	/**
	 * @return le entreprise
	 */
	public Entreprise getEntreprise() {
		return entreprise;
	}

	/**
	 * @param entreprise le entreprise à affecter
	 
	 */
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}
	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	/**
	 * @return le idDg2
	 */
	public long getIdDg2() {
		return idDg2;
	}

	/**
	 * @param idDg2 le idDg2 à affecter
	 
	 */
	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	/**
	 * @return le centreFormation
	 */
	public CentreFormation getCentreFormation() {
		return centreFormation;
	}

	/**
	 * @param centreFormation le centreFormation à affecter
	 
	 */
	public void setCentreFormation(CentreFormation centreFormation) {
		this.centreFormation = centreFormation;
	}
	
	public Tuteur getTuteur() {
		return tuteur;
	}

	public void setTuteur(Tuteur tuteur) {
		this.tuteur = tuteur;
	}
	
	/**
	 * @return le signature
	 */
	public Signature getSignature() {
		return signature;
	}

	/**
	 * @param signature le signature à affecter
	 
	 */
	public void setSignature(Signature signature) {
		this.signature = signature;
	}
	
	public String getFullName() {
		
		return getNom() + " " + getPrenom();
	}
	
	public void modifierRoles(List<UtilisateurRole> nouveauxRoles) {
        this.roles.clear();
        this.roles.addAll(nouveauxRoles);
        for (UtilisateurRole role : nouveauxRoles) {
            role.getUtilisateurs().add(this);
        }
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((cef == null) ? 0 : cef.hashCode());
		result = prime * result + ((centreFormation == null) ? 0 : centreFormation.hashCode());
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((dateDeNaissance == null) ? 0 : dateDeNaissance.hashCode());
		result = prime * result + ((entreprise == null) ? 0 : entreprise.hashCode());
		result = prime * result + ((etudiant == null) ? 0 : etudiant.hashCode());
		result = prime * result + (externalAccount ? 1231 : 1237);
		result = prime * result + ((formateur == null) ? 0 : formateur.hashCode());
		result = prime * result + (int) (idDg2 ^ (idDg2 >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((signature == null) ? 0 : signature.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((telephoneFixe == null) ? 0 : telephoneFixe.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (idDg2 != other.idDg2)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (telephoneFixe == null) {
			if (other.telephoneFixe != null)
				return false;
		} else if (!telephoneFixe.equals(other.telephoneFixe))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (civilite == null) {
			return other.civilite == null;
		} else return civilite.equals(other.civilite);
	}


	@Override
	public String toString() {
		return "Utilisateur [idDg2=" + idDg2 + ", login=" + login + ", password=" + password + ", prenom=" + prenom
				+ ", nom=" + nom + ", civilite=" + civilite + ", dateDeNaissance=" + dateDeNaissance + ", telephone="
				+ telephone + ", telephoneFixe=" + telephoneFixe + ", adresse=" + adresse + ", roles=" + roles
				+ ", entreprise=" + entreprise + ", centreFormation=" + centreFormation + ", etudiant=" + etudiant
				+ ", formateur=" + formateur + ", cef=" + cef 
				+ "version=" + this.getVersion()+ "]";
	}

	
	
}
