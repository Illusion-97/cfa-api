package fr.dawan.AppliCFABack.entities;

import java.util.List;

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

	@Column(nullable = false, length = 255)
	private String titre;

	@Column(length = 1024)
	private String contenu;

	@ManyToMany(mappedBy = "formations")
	private List<Cursus> cursusLst;
	
	@Column(nullable = true)
	private long idDg2;
	
	@Column(nullable = false, length = 255, unique = true)
	private String duration;
	
	@Column(nullable = false, length = 255, unique = true)
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

	
}
