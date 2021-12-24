package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class EtudiantDto{
	private long id;
	private UtilisateurDto utilisateurDto;
	private List<PromotionDto> promotionsDto;
	private List<GroupeEtudiantDto> groupesDto;
//	private UtilisateurDto formateurReferentDto;
//	private UtilisateurDto managerDto;
	private List<DossierProfessionnelDto>dossierProfessionnel;
	private List<DossierProjetDto> dossierProjet;


	public EtudiantDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PromotionDto> getPromotionsDto() {
		return promotionsDto;
	}

	public void setPromotionsDto(List<PromotionDto> promotionsDto) {
		this.promotionsDto = promotionsDto;
	}

	public List<GroupeEtudiantDto> getGroupesDto() {
		return groupesDto;
	}

	public void setGroupesDto(List<GroupeEtudiantDto> groupesDto) {
		this.groupesDto = groupesDto;
	}

//	public UtilisateurDto getFormateurReferentDto() {
//		return formateurReferentDto;
//	}
//
//	public void setFormateurReferentDto(UtilisateurDto formateurReferentDto) {
//		this.formateurReferentDto = formateurReferentDto;
//	}

//	public UtilisateurDto getManagerDto() {
//		return managerDto;
//	}
//
//	public void setManagerDto(UtilisateurDto managerDto) {
//		this.managerDto = managerDto;
//	}

	public List<DossierProfessionnelDto> getDossierProfessionnel() {
		return dossierProfessionnel;
	}

	public void setDossierProfessionnel(List<DossierProfessionnelDto> dossierProfessionnel) {
		this.dossierProfessionnel = dossierProfessionnel;
	}

	public List<DossierProjetDto> getDossierProjet() {
		return dossierProjet;
	}

	public void setDossierProjet(List<DossierProjetDto> dossierProjet) {
		this.dossierProjet = dossierProjet;
	}

	public UtilisateurDto getUtilisateurDto() {
		return utilisateurDto;
	}

	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
		this.utilisateurDto = utilisateurDto;
	}

	

}
