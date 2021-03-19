package fr.dawan.AppliCFABack.dto;

import java.util.List;

public class UtilisateurRoleDto {
	private long id;
	private String intitule;
	List<UtilisateurDto> utilisateurs;

	public UtilisateurRoleDto() {
		// TODO Auto-generated constructor stub
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

	public List<UtilisateurDto> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<UtilisateurDto> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
