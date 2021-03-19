package fr.dawan.AppliCFABack.dto;

<<<<<<< HEAD
public class EtudiantDto {
=======
import java.util.List;

public class EtudiantDto {
	private long id;
	private List<PromotionDto> promotions;
	private List<GroupeEtudiantDto> groupes;

	public EtudiantDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PromotionDto> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionDto> promotions) {
		this.promotions = promotions;
	}

	public List<GroupeEtudiantDto> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<GroupeEtudiantDto> groupes) {
		this.groupes = groupes;
	}
>>>>>>> refs/heads/main

}
