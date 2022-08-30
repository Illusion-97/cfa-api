package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Formation extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255) // title dans dg2
	private String titre;
	
	@Column(nullable = false)
	private long idDg2;
	
	@Column(nullable = true)
	private String slug;
	
	@Column(nullable = true)
	private String duration;
	
	@Lob
	@Column(nullable = true)
	private String objectif;
	
	@Lob
	@Column(nullable = true)
	private String prerequis;
	
	@Lob
	@Column(nullable = true)
	private String plan;
	

	@ManyToMany(mappedBy = "formations", cascade = CascadeType.ALL)
	private List<Cursus> cursusLst;


	public String getTitre() {
		return titre;
	}

	
	public Formation() {
		super();

	}
	
	public Formation(String titre, long idDg2, String slug, String duration, String objectif, String prerequis,
			String plan) {
		super();
		this.titre = titre;
		this.idDg2 = idDg2;
		this.slug = slug;
		this.duration = duration;
		this.objectif = objectif;
		this.prerequis = prerequis;
		this.plan = plan;
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
	public String getDuration() {
		return duration;
	}

	/**
	 * @param duration le duration à affecter
	 
	 */
	public void setDuration(String duration) {
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
	/**
	 * 
	 * @return liste des cursus Ids à partir de la liste des cursus
	 */
	public List<Long> getCursusLstId(){
		List<Long> cursusId = new ArrayList<Long>();
		if (cursusLst != null) {
			for(Cursus cursus : cursusLst) {
				cursusId.add(cursus.getId());
			}
		}
		return cursusId;
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursusLst == null) ? 0 : cursusLst.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + (int) (idDg2 ^ (idDg2 >>> 32));
		result = prime * result + ((objectif == null) ? 0 : objectif.hashCode());
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		result = prime * result + ((prerequis == null) ? 0 : prerequis.hashCode());
		result = prime * result + ((slug == null) ? 0 : slug.hashCode());
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
//		if (cursusLst == null) {
//			if (other.cursusLst != null)
//				return false;
//		} else if (!cursusLst.equals(other.cursusLst))
//			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (idDg2 != other.idDg2)
			return false;
		if (objectif == null) {
			if (other.objectif != null)
				return false;
		} else if (!objectif.equals(other.objectif))
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		if (prerequis == null) {
			if (other.prerequis != null)
				return false;
		} else if (!prerequis.equals(other.prerequis))
			return false;
		if (slug == null) {
			if (other.slug != null)
				return false;
		} else if (!slug.equals(other.slug))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}



}
