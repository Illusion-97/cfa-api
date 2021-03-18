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
	private AdresseDto adresse;
	@XmlElement
	private FormateurDto formateur;
	@XmlElement
	private EtudiantDto etudiant;
	@XmlElement
	private CEFDto CEF;
	@XmlElement
	private ReferentDto referent;
	@XmlElement
	private AdminDto admin;
	@XmlElement
	private List<ProjetDto> projets;

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
		this.adresse = adresseDto;
		this.formateur = formateurDto;
		this.etudiant = etudiantDto;
		this.CEF = CEFDto;
		this.referent = referentDto;
		this.admin = adminDto;
		this.projets = projetDto;
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
		return adresse;
	}

	public void setAdresseDto(AdresseDto adresseDto) {
		this.adresse = adresseDto;
	}

	public FormateurDto getFormateurDto() {
		return formateur;
	}

	public void setFormateurDto(FormateurDto formateurDto) {
		this.formateur = formateurDto;
	}

	public EtudiantDto getEtudiantDto() {
		return etudiant;
	}

	public void setEtudiantDto(EtudiantDto etudiantDto) {
		this.etudiant = etudiantDto;
	}

	public CEFDto getCEFDto() {
		return CEF;
	}

	public void setCEFDto(CEFDto cEFDto) {
		CEF = cEFDto;
	}

	public ReferentDto getReferentDto() {
		return referent;
	}

	public void setReferentDto(ReferentDto referentDto) {
		this.referent = referentDto;
	}

	public AdminDto getAdminDto() {
		return admin;
	}

	public void setAdminDto(AdminDto adminDto) {
		this.admin = adminDto;
	}

	public List<ProjetDto> getProjetDto() {
		return projets;
	}

	public void setProjetDto(List<ProjetDto> projetDto) {
		this.projets = projetDto;
	}

}
