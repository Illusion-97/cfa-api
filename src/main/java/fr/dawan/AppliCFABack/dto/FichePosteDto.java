package fr.dawan.AppliCFABack.dto;

public class FichePosteDto {

	private long id;
	private String intitule;
	private String nature;
	private String mission;
	private String compositionService;
	private String positionnement;
	private String missionPrincipale;
	private EtudiantDto etudiantDto;
	
	public FichePosteDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getCompositionService() {
		return compositionService;
	}

	public void setCompositionService(String compositionService) {
		this.compositionService = compositionService;
	}

	public String getPositionnement() {
		return positionnement;
	}

	public void setPositionnement(String positionnement) {
		this.positionnement = positionnement;
	}

	public String getMissionPrincipale() {
		return missionPrincipale;
	}

	public void setMissionPrincipale(String missionPrincipale) {
		this.missionPrincipale = missionPrincipale;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}
	

}
