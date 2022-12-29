package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Etudiant extends BaseEntity implements Serializable {

	@OneToOne
	private Utilisateur utilisateur;

	@ManyToMany(mappedBy = "etudiants",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Promotion> promotions;

	@ManyToMany(mappedBy = "etudiants")
	private List<GroupeEtudiant> groupes;

	@OneToMany(mappedBy = "etudiantNote", cascade = CascadeType.REMOVE)
	private Set<Note> notes;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DossierProfessionnel> dossierProfessionnel;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DossierProjet> dossierProjet;

	public Etudiant() {
		super();
	}

	public Etudiant(List<Promotion> promotions, List<GroupeEtudiant> groupes, List<DossierProjet> dossierProjet,
			List<DossierProfessionnel> dossierProfessionnel) {
		super();
		this.promotions = promotions;
		this.groupes = groupes;
		this.dossierProfessionnel = dossierProfessionnel;
		this.dossierProjet = dossierProjet;
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

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Etudiant)) return false;
		Etudiant etudiant = (Etudiant) o;
		return   Objects.equals(getUtilisateur(), etudiant.getUtilisateur());
	}

}
