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
	
	private List<AnnexeDossierProjetDto> annexeDossierProjetDtos;

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
	
	

}
