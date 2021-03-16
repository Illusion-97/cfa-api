package fr.dawan.AppliCFABack.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Devoir {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private String nom;
	
	@Column(nullable = false, length = 255)
	private String consigne;
	
	@Column(nullable = false, length = 255)
	private Date debut;
	
	@Column(nullable = false, length = 255)
	private Date fin;
	
	@ManyToOne
	private Cours cours;
	
	@Version
	private int version;

	public Devoir() {
		super();
	}

	public Devoir(long id, String nom, String consigne, Date debut, Date fin, Cours cours) {
		super();
		this.id = id;
		this.nom = nom;
		this.consigne = consigne;
		this.debut = debut;
		this.fin = fin;
		this.cours = cours;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
}
