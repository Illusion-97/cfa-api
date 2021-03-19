package fr.dawan.AppliCFABack.dto;

public class CEFDto {
	private long id;
	private CentreFormationDto centreFormation;

	public CEFDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CentreFormationDto getCentreFormation() {
		return centreFormation;
	}

	public void setCentreFormation(CentreFormationDto centreFormation) {
		this.centreFormation = centreFormation;
	}

}
