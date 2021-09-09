package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Etudiant extends Utilisateur {
	@ManyToMany(mappedBy = "etudiants")
	private List<Promotion> promotions;
	
	@ManyToMany(mappedBy = "etudiants")
	private List<GroupeEtudiant> groupes;
	
	@ManyToOne
	private Utilisateur formateurReferent;
	
	@ManyToOne
	private Utilisateur manager;
	
	@OneToOne
	private FichePoste fichePoste;

	public Etudiant() {
		super();
	}

	public Etudiant(List<Promotion> promotions, List<GroupeEtudiant> groupes) {
		super();
		this.promotions = promotions;
		this.groupes = groupes;
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

	public Utilisateur getFormateurReferent() {
		return formateurReferent;
	}

	public void setFormateurReferent(Utilisateur formateurReferent) {
		this.formateurReferent = formateurReferent;
	}

	public Utilisateur getManager() {
		return manager;
	}

	public void setManager(Utilisateur manager) {
		this.manager = manager;
	}

	public FichePoste getFichePoste() {
		return fichePoste;
	}

	public void setFichePoste(FichePoste fichePoste) {
		this.fichePoste = fichePoste;
	}
	
	

	
}
