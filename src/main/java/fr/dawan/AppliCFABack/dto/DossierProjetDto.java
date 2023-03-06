package fr.dawan.AppliCFABack.dto;

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

	private String nom;

	private ProjetDto projet;
	
	private EtudiantDto etudiant;
	
	private List<AnnexeDossierProjetDto> annexeDossierProjetDtos;
	
	private List<InfoDossierProjetDto> infoDossierProjetDtos;
	
	private List<ContenuDossierProjetDto> contenuDossierProjetDtos;
	
	private List<ResumeDossierProjetDto> resumeDossierProjetDtos;

	
	
	public DossierProjetDto(String nom, ProjetDto projet, List<AnnexeDossierProjetDto> annexeDossierProjetDtos,
			List<InfoDossierProjetDto> infoDossierProjetDtos, List<ContenuDossierProjetDto> contenuDossierProjetDtos,
			List<ResumeDossierProjetDto> resumeDossierProjetDtos) {
		super();
		this.nom = nom;
		this.projet = projet;
		this.annexeDossierProjetDtos = annexeDossierProjetDtos;
		this.infoDossierProjetDtos = infoDossierProjetDtos;
		this.contenuDossierProjetDtos = contenuDossierProjetDtos;
		this.resumeDossierProjetDtos = resumeDossierProjetDtos;
	}

	public DossierProjetDto() {
	    super();
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

	/**
	 * @return the projet
	 */
	public ProjetDto getProjet() {
		return projet;
	}

	/**
	 * @param projet the projet to set
	 */
	public void setProjet(ProjetDto projet) {
		this.projet = projet;
	}

    public List<AnnexeDossierProjetDto> getAnnexeDossierProjetDtos() {
        return annexeDossierProjetDtos;
    }

    public void setAnnexeDossierProjetDtos(List<AnnexeDossierProjetDto> annexeDossierProjetDtos) {
        this.annexeDossierProjetDtos = annexeDossierProjetDtos;
    }

	public List<InfoDossierProjetDto> getInfoDossierProjetDtos() {
		return infoDossierProjetDtos;
	}

	public void setInfoDossierProjetDtos(List<InfoDossierProjetDto> infoDossierProjetDtos) {
		this.infoDossierProjetDtos = infoDossierProjetDtos;
	}

	public List<ContenuDossierProjetDto> getContenuDossierProjetDtos() {
		return contenuDossierProjetDtos;
	}

	public void setContenuDossierProjetDtos(List<ContenuDossierProjetDto> contenuDossierProjetDtos) {
		this.contenuDossierProjetDtos = contenuDossierProjetDtos;
	}

	public List<ResumeDossierProjetDto> getResumeDossierProjetDtos() {
		return resumeDossierProjetDtos;
	}

	public void setResumeDossierProjetDtos(List<ResumeDossierProjetDto> resumeDossierProjetDtos) {
		this.resumeDossierProjetDtos = resumeDossierProjetDtos;
	}

	public EtudiantDto getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDto etudiant) {
		this.etudiant = etudiant;
	}
	
	

}
