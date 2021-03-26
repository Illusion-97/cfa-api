package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Etudiant extends Utilisateur {
	@ManyToMany
	private List<Promotion> promotions;
	@ManyToMany
	private List<GroupeEtudiant> groupes;

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

}
