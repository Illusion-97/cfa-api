package fr.dawan.AppliCFABack.entities;

import fr.dawan.AppliCFABack.entities.examen.SoutenanceModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@SuppressWarnings({ "serial", "unused" })
@Entity
@Getter
@Setter
public class Cursus extends BaseEntity implements Serializable { // cursus du catalogue Dev Full Stack

	@Column(nullable = false) //title dg2
	private String titre;

	@ManyToMany
	private List<Formation> formations;

	@OneToMany(mappedBy = "cursusActiviteType", cascade = CascadeType.ALL)
	private Set<ActiviteType> activiteTypes;

	@Column(nullable = false) // attribut de dg2
	private String duree;

	@Column(nullable = false, unique = true) // attribut de dg2
	private String slug;
	// pour recupérer l'id dg2
	private long idDg2;

	private int niveau ;

	private String sigle;

	private String millesime;

	private String codeTitre;

	@OneToOne(mappedBy = "cursus")
	private SoutenanceModel model;


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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duree == null) ? 0 : duree.hashCode());
		result = prime * result + (int) (idDg2 ^ (idDg2 >>> 32));
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
		Cursus other = (Cursus) obj;
		if (duree == null) {
			if (other.duree != null)
				return false;
		} else if (!duree.equals(other.duree))
			return false;
		if (idDg2 != other.idDg2)
			return false;
		if (slug == null) {
			if (other.slug != null)
				return false;
		} else if (!slug.equals(other.slug))
			return false;
		if (titre == null) {
            return other.titre == null;
		} else return titre.equals(other.titre);
    }

}
