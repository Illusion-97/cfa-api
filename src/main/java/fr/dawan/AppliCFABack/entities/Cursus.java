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

@SuppressWarnings({ "serial", "unused" })
@Entity
public class Cursus extends BaseEntity implements Serializable { // cursus du catalogue Dev Full Stack

	@Column(nullable = false, length = 255) //title dg2
	private String titre;

	@ManyToMany
	private List<Formation> formations;

	@OneToMany(mappedBy = "cursusActiviteType", cascade = CascadeType.ALL)
	private Set<ActiviteType> activiteTypes;

	@Column(nullable = false, length = 255) // attribut de dg2
	private String duree;
	
	@Column(nullable = false, length = 255, unique = true) // attribut de dg2
	private String slug;
	
	@Column(nullable = true) // pour recupérer l'id dg2
	private long idDg2;
	
	@Column(nullable = true)
	private int niveau ;
	
	@Column(nullable = true)
	private String sigle;
	
	@Column(nullable = true)
	private String millesime;
	
	@Column(nullable = true)
	private String codeTitre;
	
	
	public Cursus() {
		super();
	}

	public Cursus(String titre, List<Formation> formations) {
		super();
		this.titre = titre;
		this.formations = formations;
	}
	

	public Cursus(String titre, List<Formation> formations, Set<ActiviteType> activiteTypes,
			String duree, String slug, long idDg2) {
		super();
		this.titre = titre;
		this.formations = formations;
		this.activiteTypes = activiteTypes;
		this.duree = duree;
		this.slug = slug;
		this.idDg2 = idDg2;
	}
	
	

	public Cursus(String titre, List<Formation> formations, Set<ActiviteType> activiteTypes, String duree, String slug,
			long idDg2, int niveau, String sigle, String millesime) {
		super();
		this.titre = titre;
		this.formations = formations;
		this.activiteTypes = activiteTypes;
		this.duree = duree;
		this.slug = slug;
		this.idDg2 = idDg2;
		this.niveau = niveau;
		this.sigle = sigle;
		this.millesime = millesime;
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

	/**
	 * @return le niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau le niveau à affecter
	 
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return le sigle
	 */
	public String getSigle() {
		return sigle;
	}

	/**
	 * @param sigle le sigle à affecter
	 
	 */
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	
	/**
	 * @return le millesime
	 */
	
	public String getMillesime() {
		return millesime;
	}

	/**
	 * @param millesime 
	 * le millesime à affecter
	 
	 */
	
	public void setMillesime(String millesime) {
		this.millesime = millesime;
	}

	/**
	 * @return le codeTitre
	 */
	
	public String getCodeTitre() {
		return codeTitre;
	}

	/**
	 * @param codeTitre 
	 * le codeTitre à affecter
	 
	 */
	
	public void setCodeTitre(String codeTitre) {
		this.codeTitre = codeTitre;
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
