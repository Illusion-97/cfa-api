package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

	@ManyToOne
	private Tuteur tuteur;

	public Etudiant() {
		super();
	}

	public Etudiant(List<Promotion> promotions, List<GroupeEtudiant> groupes, List<DossierProjet> dossierProjet, Tuteur tuteur) {
		super();
		this.promotions = promotions;
		this.groupes = groupes;
		this.tuteur=tuteur;
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
	

	public Tuteur getTuteur() {
		return tuteur;
	}

	public void setTuteur(Tuteur tuteur) {
		this.tuteur = tuteur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupes == null) ? 0 : groupes.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((promotions == null) ? 0 : promotions.hashCode());
		result = prime * result + ((tuteur == null) ? 0 : tuteur.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Etudiant)) return false;
		Etudiant etudiant = (Etudiant) o;
		return   Objects.equals(getUtilisateur(), etudiant.getUtilisateur());
	}


}
