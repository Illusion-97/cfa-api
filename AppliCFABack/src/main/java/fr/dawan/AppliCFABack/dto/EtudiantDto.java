package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class EtudiantDto extends UtilisateurDto{
	private long id;
	private List<PromotionDto> promotionsDto;
	private List<GroupeEtudiantDto> groupesDto;
	private UtilisateurDto formateurReferentDto;
	private UtilisateurDto manageurDto;

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

	public UtilisateurDto getManageurDto() {
		return manageurDto;
	}

	public void setManageurDto(UtilisateurDto manageurDto) {
		this.manageurDto = manageurDto;
	}
	
}
