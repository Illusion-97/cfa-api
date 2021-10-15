package fr.dawan.AppliCFABack.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Examen { // examen Java

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 255)
	private String enonce;

	@ManyToOne
	private Formation formation;

	@ManyToOne
	private Cursus cursus;

	public Examen() {
		super();
	}

	public Examen(String enonce, Formation formation, Cursus cursus) {
		super();
		this.enonce = enonce;
		this.formation = formation;
		this.cursus = cursus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

}
