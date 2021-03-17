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
	private CEFDto CEFDto;
	@XmlElement
	private ReferentDto referentDto;
	@XmlElement
	private AdminDto adminDto;
	@XmlElement
	private List<ProjetDto> projetDto;

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
		this.adresseDto = adresseDto;
		this.formateurDto = formateurDto;
		this.etudiantDto = etudiantDto;
		this.CEFDto = CEFDto;
		this.referentDto = referentDto;
		this.adminDto = adminDto;
		this.projetDto = projetDto;
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

	public CEFDto getCEFDto() {
		return CEFDto;
	}

	public void setCEFDto(CEFDto cEFDto) {
		CEFDto = cEFDto;
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

	public List<ProjetDto> getProjetDto() {
		return projetDto;
	}

	public void setProjetDto(List<ProjetDto> projetDto) {
		this.projetDto = projetDto;
	}

}
