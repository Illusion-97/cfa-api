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
	private long idAdresse;
	@XmlElement
	private long numero;
	@XmlElement
	private String rue;
	@XmlElement
	private String ville;
	@XmlElement
	private String codePostal;
	@XmlElement
	private List<PersonneDto> personnes;
	@XmlElement
	private CentreDto centre;
	@XmlElement
	private EntrepriseDto entreprise;

	public AdresseDto() {
		super();
	}

	public AdresseDto(long idAdresse, long numero, String rue, String ville, String codePostal, List<PersonneDto> personnes,
			CentreDto centre, EntrepriseDto entreprise) {
		super();
		this.idAdresse = idAdresse;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.personnes = personnes;
		this.centre = centre;
		this.entreprise = entreprise;
	}

	public long getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(long idAdresse) {
		this.idAdresse = idAdresse;
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

	public List<PersonneDto> getPersonneDto() {
		return personnes;
	}

	public void setPersonneDto(List<PersonneDto> personnes) {
		this.personnes = personnes;
	}

	public CentreDto getCentreDto() {
		return centre;
	}

	public void setCentreDto(CentreDto centre) {
		this.centre = centre;
	}

	public EntrepriseDto getEntrepriseDto() {
		return entreprise;
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
		this.entreprise = entrepriseDto;
	}


}
