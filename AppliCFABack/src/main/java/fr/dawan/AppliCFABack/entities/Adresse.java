package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Adresse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length = 255)
	private long numero;
	
	@Column(nullable = false, length = 255)
	private String rue;
	
	@Column(nullable = false, length = 255)
	private String ville;
	
	@Column(nullable = false, length = 255)
	private String codePostal;	
	
	@OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL)
	private List<Personne> personnes;
	
	@OneToOne
	private Centre centre;
	
	@OneToOne
	private Entreprise entreprise;
	
	@Version
	private int version;

	public Adresse() {
		super();
	}

	public Adresse(long id, long numero, String rue, String ville, String codePostal, List<Personne> personne, Centre centre,
			Entreprise entreprise) {
		super();
		this.id = id;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.personnes = personne;
		this.centre = centre;
		this.entreprise = entreprise;
	}
	
	public Adresse(long id, long numero, String rue, String ville, String codePostal) {
		super();
		this.id = id;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public List<Personne> getPersonne() {
		return personnes;
	}

	public void setPersonne(List<Personne> personne) {
		this.personnes = personne;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
