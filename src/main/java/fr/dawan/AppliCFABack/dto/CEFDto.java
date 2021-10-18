package fr.dawan.AppliCFABack.dto;

public class CEFDto{
	private long id;
	private UtilisateurDto personneDto;
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

	public UtilisateurDto getPersonneDto() {
		return personneDto;
	}

	public void setPersonneDto(UtilisateurDto personneDto) {
		this.personneDto = personneDto;
	}

}
