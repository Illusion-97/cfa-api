package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Etudiant{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Utilisateur utilisateur;
	
	@ManyToMany(mappedBy = "etudiants")
	private List<Promotion> promotions;
	
	@ManyToMany(mappedBy = "etudiants")
	private List<GroupeEtudiant> groupes;
	
//	@ManyToOne
//	private Utilisateur formateurReferent;
	
//	@ManyToOne
//	private Utilisateur manager;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<DossierProfessionnel> dossierProfessionnel;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<DossierProjet> dossierProjet;


	public Etudiant() {
		super();
	}

	public Etudiant(List<Promotion> promotions, List<GroupeEtudiant> groupes,List<DossierProjet> dossierProjet, List<DossierProfessionnel> dossierProfessionnel) {
		super();
		this.promotions = promotions;
		this.groupes = groupes;
		this.dossierProfessionnel=dossierProfessionnel;
		this.dossierProjet=dossierProjet;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<GroupeEtudiant> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<GroupeEtudiant> groupes) {
		this.groupes = groupes;
	}

//	public Utilisateur getFormateurReferent() {
//		return formateurReferent;
//	}
//
//	public void setFormateurReferent(Utilisateur formateurReferent) {
//		this.formateurReferent = formateurReferent;
//	}

//	public Utilisateur getManager() {
//		return manager;
//	}
//
//	public void setManager(Utilisateur manager) {
//		this.manager = manager;
//	}

	public List<DossierProfessionnel> getDossierProfessionnel() {
		return dossierProfessionnel;
	}

	public void setDossierProfessionnel(List<DossierProfessionnel> dossierProfessionnel) {
		this.dossierProfessionnel = dossierProfessionnel;
	}

	public List<DossierProjet> getDossierProjet() {
		return dossierProjet;
	}

	public void setDossierProjet(List<DossierProjet> dossierProjet) {
		this.dossierProjet = dossierProjet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	
}
