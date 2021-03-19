package fr.dawan.AppliCFABack.dto;

public class ProjetDto {
	private long id;
	private String nom;
	private String description;
	private String pjCahierDesCharges;
	private GroupeEtudiantDto groupe;

	public ProjetDto() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPjCahierDesCharges() {
		return pjCahierDesCharges;
	}

	public void setPjCahierDesCharges(String pjCahierDesCharges) {
		this.pjCahierDesCharges = pjCahierDesCharges;
	}

	public GroupeEtudiantDto getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeEtudiantDto groupe) {
		this.groupe = groupe;
	}

}
