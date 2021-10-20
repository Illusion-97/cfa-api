package fr.dawan.AppliCFABack.dto;

public class MaitreApprentissageDto extends UtilisateurDto{

	private long id;
	private EntrepriseDto entrepriseDto;
	
	public MaitreApprentissageDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}

}
