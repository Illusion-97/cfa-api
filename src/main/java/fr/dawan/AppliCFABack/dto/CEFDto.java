package fr.dawan.AppliCFABack.dto;

public class CEFDto extends UtilisateurDto {
	private long id;
	private CentreFormationDto centreFormationDto;

	public CEFDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CentreFormationDto getCentreFormationDto() {
		return centreFormationDto;
	}

	public void setCentreFormationDto(CentreFormationDto centreFormationDto) {
		this.centreFormationDto = centreFormationDto;
	}

}
