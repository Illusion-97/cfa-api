package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Entity
public class DossierProfessionnel extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String nom;

	@ManyToOne
	private Cursus cursus;

	@OneToMany(mappedBy = "dossierProfessionnel", cascade = CascadeType.ALL)
	private List<ExperienceProfessionnelle> experienceProfessionnelles;

	@ManyToOne
	private Etudiant etudiant;

	@OneToMany(mappedBy = "dossierProfessionnel", cascade = CascadeType.ALL)
	private List<Annexe> annexes;
	
	@OneToMany(mappedBy = "dossierProfessionnel", cascade = CascadeType.ALL)
	private List<Facultatif> facultatifs;
	
	@Column(name = "fileImport", nullable = true)
	private String fileImport;


	public DossierProfessionnel() {
		super();
	}

	public DossierProfessionnel(String nom, Cursus cursus, List<ExperienceProfessionnelle> experienceProfessionnelles,
			Etudiant etudiant, List<Annexe> annexes, List<Facultatif> facultatifs, String fileImport) {
		super();
		this.nom = nom;
		this.cursus = cursus;
		this.experienceProfessionnelles = experienceProfessionnelles;
		this.etudiant = etudiant;
		this.annexes = annexes;
		this.facultatifs = facultatifs;
		this.fileImport = fileImport;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	

	public Cursus getCursus() {
		return cursus;
	}

	public void setCursus(Cursus cursus) {
		this.cursus = cursus;
	}

	public List<ExperienceProfessionnelle> getExperienceProfessionnelles() {
		return experienceProfessionnelles;
	}

	public void setExperienceProfessionnelles(List<ExperienceProfessionnelle> experienceProfessionnelles) {
		this.experienceProfessionnelles = experienceProfessionnelles;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public List<Annexe> getAnnexes() {
		return annexes;
	}

	public void setAnnexes(List<Annexe> annexes) {
		this.annexes = annexes;
	}

	public List<Facultatif> getFacultatifs() {
		return facultatifs;
	}

	public void setFacultatifs(List<Facultatif> facultatifs) {
		this.facultatifs = facultatifs;
	}

	public String getFileImport() {
		return fileImport;
	}

	public void setFileImport(String fileImport) {
		this.fileImport = fileImport;
	}

	
	
	
}
