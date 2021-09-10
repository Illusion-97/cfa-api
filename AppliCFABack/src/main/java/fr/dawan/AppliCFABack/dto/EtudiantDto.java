package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class EtudiantDto extends UtilisateurDto {
	private long id;
	private List<PromotionDto> promotionsDto;
	private List<GroupeEtudiantDto> groupesDto;
	private UtilisateurDto formateurReferentDto;
	private UtilisateurDto managerDto;

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

	public UtilisateurDto getFormateurReferentDto() {
		return formateurReferentDto;
	}

	public void setFormateurReferentDto(UtilisateurDto formateurReferentDto) {
		this.formateurReferentDto = formateurReferentDto;
	}

	public UtilisateurDto getManagerDto() {
		return managerDto;
	}

	public void setManagerDto(UtilisateurDto managerDto) {
		this.managerDto = managerDto;
	}

}
