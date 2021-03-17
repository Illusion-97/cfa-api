package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "admin")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdminDto implements Serializable {
	@XmlElement
	private long idAdmin;
	@XmlElement
	private PersonneDto personne;

	
	public AdminDto() {
		super();
	}

	public AdminDto(long idAdmin, PersonneDto personne) {
		super();
		this.idAdmin = idAdmin;
		this.personne = personne;
	}

	public long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
	}

	public PersonneDto getPersonneDto() {
		return personne;
	}

	public void setPersonneDto(PersonneDto personne) {
		this.personne = personne;
	}

	
}
