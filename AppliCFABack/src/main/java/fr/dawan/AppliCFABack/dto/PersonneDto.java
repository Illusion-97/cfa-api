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
	private CEFDto cefDto;
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private AdminDto adminDto;
	@XmlElement
	private List<ProjetDto> projetsDto;

	public PersonneDto() {
		super();
	}
	
	public PersonneDto(long id, String login, String password, String prenom, String nom, AdresseDto adresse,
			FormateurDto formateur, EtudiantDto etudiant, CEFDto cef, ReferentDto referent, AdminDto admin,
			List<ProjetDto> projets) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.adresseDto = adresse;
		this.formateurDto = formateur;
		this.etudiantDto = etudiant;
		this.cefDto = cef;
		this.referentDto = referent;
		this.adminDto = admin;
		this.projetsDto = projets;
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
		return adresseDto;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresseDto = adresseDto;
	}

	public FormateurDto getFormateurDto() {
		return formateurDto;
	}

	public void setFormateurDto(FormateurDto formateurDto) {
		this.formateurDto = formateurDto;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiantDto;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiantDto = etudiantDto;
	}

	public CEFDto getCefDto() {
		return cefDto;
	}

	public void setCefDto(CEFDto cefDto) {
		this.cefDto = cefDto;
	}

	public ReferentDto getReferentDto() {
		return referentDto;
	}

	public void setReferentDto(ReferentDto referentDto) {
		this.referentDto = referentDto;
	}

	public AdminDto getAdminDto() {
		return adminDto;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.adminDto = adminDto;
	}

	public List<ProjetDto> getProjetsDto() {
		return projetsDto;
	}

	public void setProjetsDto(List<ProjetDto> projetsDto) {
		this.projetsDto = projetsDto;
	}

}
