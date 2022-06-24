package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Formation extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255) // title dans dg2
	private String titre;
	
	@Column(nullable = false)
	private long idDg2;
	
	private String slug;
	
	@Column(nullable = false)
	private double duration;
	
	@Column(nullable = true)
	private String objectif;
	
	private String prerequis;
	
	private String plan;

	@ManyToMany(mappedBy = "formations", cascade = CascadeType.ALL)
	private List<Cursus> cursusLst;

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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
	 * @return le slug
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * @param slug le slug à affecter
	 
	 */
	public void setSlug(String slug) {
		this.slug = slug;
	}

	/**
	 * @return le duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * @param duration le duration à affecter
	 
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	public List<Cursus> getCursusLst() {
		return cursusLst;
	}

	public void setCursusLst(List<Cursus> cursusLst) {
		this.cursusLst = cursusLst;
	}


	/**
	 * @return le objectif
	 */
	public String getObjectif() {
		return objectif;
	}

	/**
	 * @param objectif le objectif à affecter
	 
	 */
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	/**
	 * @return le prerequis
	 */
	public String getPrerequis() {
		return prerequis;
	}

	/**
	 * @param prerequis le prerequis à affecter
	 
	 */
	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}

	/**
	 * @return le plan
	 */
	public String getPlan() {
		return plan;
	}

	/**
	 * @param plan le plan à affecter
	 
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursusLst == null) ? 0 : cursusLst.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
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
		Formation other = (Formation) obj;
		if (cursusLst == null) {
			if (other.cursusLst != null)
				return false;
		} else if (!cursusLst.equals(other.cursusLst))
			return false;
		if (titre == null) {
			return other.titre == null;
		} else return titre.equals(other.titre);
	}



}
