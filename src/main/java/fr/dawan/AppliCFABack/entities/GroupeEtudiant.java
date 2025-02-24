package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity
public class GroupeEtudiant extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@ManyToMany
	private List<Etudiant> etudiants;

	@OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
	private List<Projet> projets;

	public GroupeEtudiant() {
		super();
	}

	public GroupeEtudiant(String nom, List<Etudiant> etudiants) {
		super();
		this.nom = nom;
		this.etudiants = etudiants;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
}
