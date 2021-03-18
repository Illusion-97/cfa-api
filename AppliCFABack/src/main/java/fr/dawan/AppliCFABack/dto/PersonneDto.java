package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "personne")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonneDto implements Serializable {
	@XmlElement
	private long id;
	@XmlElement
	private String login;
	@XmlElement
	private String password;
	@XmlElement
	private String prenom;
	@XmlElement
	private String nom;
	@XmlElement
	private AdresseDto adresseDto;
	@XmlElement
	private FormateurDto formateurDto;
	@XmlElement
	private EtudiantDto etudiantDto;
	@XmlElement
<<<<<<< HEAD
	private CEFDto cefDto;
=======
	private CEFDto CEF;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private AdminDto adminDto;
	@XmlElement
	private List<ProjetDto> projetsDto;

	public PersonneDto() {
		super();
	}
	
	public PersonneDto(long id, String login, String password, String prenom, String nom, AdresseDto adresseDto,
			FormateurDto formateurDto, EtudiantDto etudiantDto, CEFDto CEFDto, ReferentDto referentDto, AdminDto adminDto,
			List<ProjetDto> projetDto) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
<<<<<<< HEAD
		this.adresseDto = adresse;
		this.formateurDto = formateur;
		this.etudiantDto = etudiant;
		this.cefDto = cef;
		this.referentDto = referent;
		this.adminDto = admin;
		this.projetsDto = projets;
=======
		this.adresse = adresseDto;
		this.formateur = formateurDto;
		this.etudiant = etudiantDto;
		this.CEF = CEFDto;
		this.referent = referentDto;
		this.admin = adminDto;
		this.projets = projetDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public AdresseDto getAdresseDto() {
<<<<<<< HEAD
		return adresseDto;
=======
		return adresse;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setAdresseDto(AdresseDto adresseDto) {
<<<<<<< HEAD
		this.adresseDto = adresseDto;
=======
		this.adresse = adresseDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public FormateurDto getFormateurDto() {
<<<<<<< HEAD
		return formateurDto;
=======
		return formateur;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setFormateurDto(FormateurDto formateurDto) {
<<<<<<< HEAD
		this.formateurDto = formateurDto;
=======
		this.formateur = formateurDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public EtudiantDto getEtudiantDto() {
<<<<<<< HEAD
		return etudiantDto;
=======
		return etudiant;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
<<<<<<< HEAD
		this.etudiantDto = etudiantDto;
=======
		this.etudiant = etudiantDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public CEFDto getCefDto() {
		return cefDto;
=======
	public CEFDto getCEFDto() {
		return CEF;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
=======
	public void setCEFDto(CEFDto cEFDto) {
		CEF = cEFDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public ReferentDto getReferentDto() {
<<<<<<< HEAD
		return referentDto;
=======
		return referent;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setReferentDto(ReferentDto referentDto) {
<<<<<<< HEAD
		this.referentDto = referentDto;
=======
		this.referent = referentDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public AdminDto getAdminDto() {
<<<<<<< HEAD
		return adminDto;
=======
		return admin;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

	public void setAdminDto(AdminDto adminDto) {
<<<<<<< HEAD
		this.adminDto = adminDto;
=======
		this.admin = adminDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public List<ProjetDto> getProjetsDto() {
		return projetsDto;
=======
	public List<ProjetDto> getProjetDto() {
		return projets;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

<<<<<<< HEAD
	public void setProjetsDto(List<ProjetDto> projetsDto) {
		this.projetsDto = projetsDto;
=======
	public void setProjetDto(List<ProjetDto> projetDto) {
		this.projets = projetDto;
>>>>>>> branch 'main' of https://github.com/Dwena/AppliCFABack
	}

}
