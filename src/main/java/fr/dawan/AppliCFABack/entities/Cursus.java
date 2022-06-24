package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cursus extends BaseEntity implements Serializable { // cursus du catalogue Dev Full Stack

	@Column(nullable = false, length = 255) //title dg2
	private String titre;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Formation> formations;

	@OneToMany(mappedBy = "cursusActiviteType", cascade = CascadeType.ALL)
	private Set<ActiviteType> activiteTypes;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false, length = 255) // attribut de dg2
	private String duree;
	
	@Column(nullable = false, length = 255, unique = true) // attribut de dg2
	private String slug;
	
	@Column(nullable = true) // pour recupérer l'id dg2
	private long idDg2;

	public Cursus() {
		super();
	}

	public Cursus(String titre, List<Formation> formations) {
		super();
		this.titre = titre;
		this.formations = formations;
	}
	

	public Cursus(String titre, List<Formation> formations, Set<ActiviteType> activiteTypes, String description,
			String duree, String slug, long idDg2) {
		super();
		this.titre = titre;
		this.formations = formations;
		this.activiteTypes = activiteTypes;
		this.description = description;
		this.duree = duree;
		this.slug = slug;
		this.idDg2 = idDg2;
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

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duree, idDg2, slug, titre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cursus other = (Cursus) obj;
		return Objects.equals(duree, other.duree) && idDg2 == other.idDg2 && Objects.equals(slug, other.slug)
				&& Objects.equals(titre, other.titre);
	}
}
