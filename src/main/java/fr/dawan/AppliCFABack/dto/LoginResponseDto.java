package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "login-reponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponseDto implements Serializable{

//	private UtilisateurDto utilisateurDto;
	private String token;	

	public LoginResponseDto() {
		
	}
	
	
	public LoginResponseDto(String token) {
		super();
		this.token = token;
	}

//	public LoginResponseDto( UtilisateurDto utilisateurDto, String token) {
//		super();
//		this.token = token;
//		this.utilisateurDto = utilisateurDto;
//	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


//	public UtilisateurDto getUtilisateurDto() {
//		return utilisateurDto;
//	}
//
//
//	public void setUtilisateurDto(UtilisateurDto utilisateurDto) {
//		this.utilisateurDto = utilisateurDto;
//	}
		
	
}

