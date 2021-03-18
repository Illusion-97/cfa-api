package fr.dawan.AppliCFABack.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Etudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	/*
	@Column(nullable = false, length = 255)
	private List<File> fiches;
	*/
	
	@OneToOne
	private Personne personne;
	
	@OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
	private List<Absence> abscences;
	
	@ManyToOne
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
	private List<Note> notes;
	
	@ManyToMany(mappedBy = "etudiants")
	private List<ProgrammePromotion> programmePromotions;
	
	@ManyToMany(mappedBy = "etudiants")
	private List<Groupe> groupes;
	
	@Version
	private int version;

	public Etudiant() {
		super();
	}

	public Etudiant(long id/*, List<File> fiches*/, Personne personne, List<Absence> abscences, Entreprise entreprise,
			List<ProgrammePromotion> programmePromotion, List<Groupe> groupes) {
		super();
		this.id = id;
//		this.fiches = fiches;
		this.personne = personne;
		this.abscences = abscences;
		this.entreprise = entreprise;
		this.programmePromotions = programmePromotion;
		this.groupes = groupes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
/*
	public List<File> getFiches() {
		return fiches;
	}

	public void setFiches(List<File> fiches) {
		this.fiches = fiches;
	}
*/
	public Personne getPersonne() {
		return personne;
	}

	public void setUser(Personne personne) {
		this.personne = personne;
	}

	public List<Absence> getAbscences() {
		return abscences;
	}

	public void setAbscences(List<Absence> abscences) {
		this.abscences = abscences;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<ProgrammePromotion> getProgrammePromotion() {
		return programmePromotions;
	}

	public void setProgrammePromotion(List<ProgrammePromotion> programmePromotion) {
		this.programmePromotions = programmePromotion;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}	
	
}
