package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import fr.dawan.AppliCFABack.entities.Tuteur;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Etudiant Entity
 */
@SuppressWarnings("serial")
public class EtudiantDto extends BaseEntityDto implements Serializable {

	private UtilisateurDto utilisateurDto;
	private List<PromotionDto> promotionsDto;
	private List<GroupeEtudiantDto> groupesDto;
	private List<DossierProfessionnelDto> dossierProfessionnel;
	private List<DossierProjetDto> dossierProjet;
	private TuteurDto tuteurDto;

	public EtudiantDto() {
		super();
	}

	/**
	 * @return the utilisateurDto
	 */
	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	/**
	 * @param utilisateurDto the utilisateurDto to set
	 */
	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	/**
	 * @return the promotionsDto
	 */
	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	/**
	 * @param promotionsDto the promotionsDto to set
	 */
	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	/**
	 * @return the groupesDto
	 */
	public List<GroupeEtudiantDto> getGroupesDto() {
		return groupesDto;
	}

	/**
	 * @param groupesDto the groupesDto to set
	 */
	public void setGroupesDto(List<GroupeEtudiantDto> groupesDto) {
		this.groupesDto = groupesDto;
	}

	/**
	 * @return the dossierProfessionnel
	 */
	public List<DossierProfessionnelDto> getDossierProfessionnel() {
		return dossierProfessionnel;
	}

	/**
	 * @param dossierProfessionnel the dossierProfessionnel to set
	 */
	public void setDossierProfessionnel(List<DossierProfessionnelDto> dossierProfessionnel) {
		this.dossierProfessionnel = dossierProfessionnel;
	}

	/**
	 * @return the dossierProjet
	 */
	public List<DossierProjetDto> getDossierProjet() {
		return dossierProjet;
	}

	/**
	 * @param dossierProjet the dossierProjet to set
	 */
	public void setDossierProjet(List<DossierProjetDto> dossierProjet) {
		this.dossierProjet = dossierProjet;
	}

	public TuteurDto getTuteurDto() {
		return tuteurDto;
	}

	public void setTuteurDto(TuteurDto tuteurDto) {
		this.tuteurDto = tuteurDto;
	}
	
	

}
