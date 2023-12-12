package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.EtudiantDossierProjetDto;
import fr.dawan.AppliCFABack.dto.customdtos.dossierprojet.ProjetDossierProjetDto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Dossier projet Entity
 */
@SuppressWarnings("serial")
public class DossierProjetDto extends BaseEntityDto implements Serializable {
	private long id;
	private String nom;
	private String fullName;
	private String dossierImport;
	private EtudiantDossierProjetDto etudiant;
	private ProjetDossierProjetDto projet;
	private List<String> annexeDossierProjets;
	private List<String> infoDossierProjets;
	private List<Long> competenceProfessionnelleIds;
	private List<String> contenuDossierProjets;
	private List<String> resumeDossierProjets;
	private int version;

	public DossierProjetDto() {
	    super();
	}

	public DossierProjetDto(long id, String nom, String fullName, String dossierImport,
			EtudiantDossierProjetDto etudiant, ProjetDossierProjetDto projet, List<String> annexeDossierProjets,
			List<String> infoDossierProjets, List<Long> competenceProfessionnelleIds,
			List<String> contenuDossierProjets, List<String> resumeDossierProjets, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.fullName = fullName;
		this.dossierImport = dossierImport;
		this.etudiant = etudiant;
		this.projet = projet;
		this.annexeDossierProjets = annexeDossierProjets;
		this.infoDossierProjets = infoDossierProjets;
		this.competenceProfessionnelleIds = competenceProfessionnelleIds;
		this.contenuDossierProjets = contenuDossierProjets;
		this.resumeDossierProjets = resumeDossierProjets;
		this.version = version;
	}

	public DossierProjetDto(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getDossierImport() {
		return dossierImport;
	}

	public void setDossierImport(String dossierImport) {
		this.dossierImport = dossierImport;
	}

	public ProjetDossierProjetDto getProjet() {
		return projet;
	}

	public void setProjet(ProjetDossierProjetDto projet) {
		this.projet = projet;
	}

	public EtudiantDossierProjetDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDossierProjetDto etudiant) {
		this.etudiant = etudiant;
	}

	public List<String> getAnnexeDossierProjets() {
		return annexeDossierProjets;
	}

	public void setAnnexeDossierProjets(List<String> annexeDossierProjets) {
		this.annexeDossierProjets = annexeDossierProjets;
	}

	public List<String> getInfoDossierProjets() {
		return infoDossierProjets;
	}

	public void setInfoDossierProjets(List<String> infoDossierProjets) {
		this.infoDossierProjets = infoDossierProjets;
	}

	public List<String> getContenuDossierProjets() {
		return contenuDossierProjets;
	}

	public void setContenuDossierProjets(List<String> contenuDossierProjets) {
		this.contenuDossierProjets = contenuDossierProjets;
	}

	public List<String> getResumeDossierProjets() {
		return resumeDossierProjets;
	}

	public void setResumeDossierProjets(List<String> resumeDossierProjets) {
		this.resumeDossierProjets = resumeDossierProjets;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public void setVersion(int version) {
		this.version = version;
	}

	public List<Long> getCompetenceProfessionnelleIds() {
		return competenceProfessionnelleIds;
	}

	public void setCompetenceProfessionnelleIds(List<Long> competenceProfessionnelleIds) {
		this.competenceProfessionnelleIds = competenceProfessionnelleIds;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
