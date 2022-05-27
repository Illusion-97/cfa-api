package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cursus extends BaseEntity implements Serializable { // cursus du catalogue Dev Full Stack

	@Column(nullable = false, length = 255)
	private String titre;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Formation> formations;

	@OneToMany(mappedBy = "cursusActiviteType", cascade = CascadeType.ALL)
	private Set<ActiviteType> activiteTypes;

	@Column(columnDefinition = "TEXT")
	private String description;

	private int duree;

	public Cursus() {
		super();
	}

	public Cursus(String titre, List<Formation> formations) {
		super();
		this.titre = titre;
		this.formations = formations;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Formation> getFormations() {
		return formations;
	}

	public void setFormations(List<Formation> formations) {
		this.formations = formations;
	}

	public Set<ActiviteType> getActiviteTypes() {
		return activiteTypes;
	}

	public void setActiviteTypes(Set<ActiviteType> activiteTypes) {
		this.activiteTypes = activiteTypes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

}
