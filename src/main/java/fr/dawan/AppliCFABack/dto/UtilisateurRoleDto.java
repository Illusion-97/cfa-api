package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class UtilisateurRoleDto extends BaseEntityDto implements Serializable {

	private String intitule;
	List<UtilisateurDto> utilisateursDto;

	public UtilisateurRoleDto() {
		// TODO Auto-generated constructor stub
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public List<UtilisateurDto> getUtilisateursDto() {
		return utilisateursDto;
	}

	public void setUtilisateursDto(List<UtilisateurDto> utilisateursDto) {
		this.utilisateursDto = utilisateursDto;
	}

	@Override
	public String toString() {
		return "UtilisateurRoleDto{" + "id=" + id + ", intitule='" + intitule + '\'' + ", utilisateursDto="
				+ utilisateursDto + '}';
	}
}
