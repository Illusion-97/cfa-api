package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "adresse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdresseDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private long numero;
	@XmlElement
	private String rue;
	@XmlElement
	private String ville;
	@XmlElement
	private String codePostal;
	@XmlElement
	private List<PersonneDto> personnesDto;
	@XmlElement
	private CentreDto centreDto;
	@XmlElement
	private EntrepriseDto entrepriseDto;

	public AdresseDto() {
		super();
	}

	public AdresseDto(long id, long numero, String rue, String ville, String codePostal, List<PersonneDto> personnesDto,
			CentreDto centreDto, EntrepriseDto entrepriseDto) {
		super();
		this.id = id;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.personnesDto = personnesDto;
		this.centreDto = centreDto;
		this.entrepriseDto = entrepriseDto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public List<PersonneDto> getPersonnesDto() {
		return personnesDto;
	}

	public void setPersonnesDto(List<PersonneDto> personnesDto) {
		this.personnesDto = personnesDto;
	}

	public CentreDto getCentreDto() {
		return centreDto;
	}

	public void setCentreDto(CentreDto centreDto) {
		this.centreDto = centreDto;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entrepriseDto;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entrepriseDto = entrepriseDto;
	}


}
