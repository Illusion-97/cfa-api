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
<<<<<<< HEAD
	private List<PersonneDto> personnesDto;
=======
	private List<PersonneDto> personne;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
	private CentreDto centreDto;
	@XmlElement
	private EntrepriseDto entrepriseDto;

	public AdresseDto() {
		super();
	}

	public AdresseDto(long id, long numero, String rue, String ville, String codePostal, List<PersonneDto> personneDto,
			CentreDto centreDto, EntrepriseDto entrepriseDto) {
		super();
		this.id = id;
		this.numero = numero;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
<<<<<<< HEAD
		this.personnesDto = personnes;
		this.centreDto = centre;
		this.entrepriseDto = entreprise;
=======
		this.personne = personneDto;
		this.centre = centreDto;
		this.entreprise = entrepriseDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
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

<<<<<<< HEAD
	public List<PersonneDto> getPersonnesDto() {
		return personnesDto;
=======
	public List<PersonneDto> getPersonneDto() {
		return personne;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setPersonnesDto(List<PersonneDto> personnesDto) {
		this.personnesDto = personnesDto;
=======
	public void setPersonneDto(List<PersonneDto> personneDto) {
		this.personne = personneDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public CentreDto getCentreDto() {
<<<<<<< HEAD
		return centreDto;
=======
		return centre;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setCentreDto(CentreDto centreDto) {
<<<<<<< HEAD
		this.centreDto = centreDto;
=======
		this.centre = centreDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public EntrepriseDto getEntrepriseDto() {
<<<<<<< HEAD
		return entrepriseDto;
=======
		return entreprise;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setEntrepriseDto(EntrepriseDto entrepriseDto) {
<<<<<<< HEAD
		this.entrepriseDto = entrepriseDto;
=======
		this.entreprise = entrepriseDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}



}
