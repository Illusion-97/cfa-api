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

	@Column(nullable = true, length = 1024)
	private String contenu;

	@ManyToMany(mappedBy = "formations", cascade = CascadeType.ALL)
	private List<Cursus> cursusLst;


	public Formation() {
		super();
	}

	public Formation(String titre, String contenu, List<Cursus> cursusLst) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.cursusLst = cursusLst;
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
