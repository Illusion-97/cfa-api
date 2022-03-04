package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255) // title dans dg2
	private String titre;

	@Column(nullable= true, length = 1024)
	private String contenu;

	@ManyToMany(mappedBy = "formations", cascade = CascadeType.ALL)
	private List<Cursus> cursusLst;
	
	@Column(nullable = true) //pour recupérer l'id dg2
	private long idDg2;
	
	@Column(nullable = false, length = 255, unique = true) // attribut de dg2
	private String duration;
	
	@Column(nullable = false, length = 255, unique = true) // attribut de dg2
	private String slug;

	public Formation() {
		super();
	}

	public Formation(String titre, String contenu, List<Cursus> cursusLst) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.cursusLst = cursusLst;
	}

	public Formation(long id, String titre, String contenu, List<Cursus> cursusLst, long idDg2, String duration,
			String slug) {
		super();
		this.id = id;
		this.titre = titre;
		this.contenu = contenu;
		this.cursusLst = cursusLst;
		this.idDg2 = idDg2;
		this.duration = duration;
		this.slug = slug;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public List<Cursus> getCursusLst() {
		return cursusLst;
	}

	public void setCursusLst(List<Cursus> cursusLst) {
		this.cursusLst = cursusLst;
	}

	public long getIdDg2() {
		return idDg2;
	}

	public void setIdDg2(long idDg2) {
		this.idDg2 = idDg2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursusLst == null) ? 0 : cursusLst.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
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
		if (cursusLst == null) {
			if (other.cursusLst != null)
				return false;
		} else if (!cursusLst.equals(other.cursusLst))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}
	
	
	
}
