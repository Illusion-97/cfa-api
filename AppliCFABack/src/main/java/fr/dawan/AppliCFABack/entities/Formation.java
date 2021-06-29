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

	public Formation() {
		super();
	}

	public Formation(String titre, String contenu, List<Cursus> cursusLst) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.cursusLst = cursusLst;
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

}
