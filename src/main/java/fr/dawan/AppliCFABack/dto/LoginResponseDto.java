package fr.dawan.AppliCFABack.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement(name = "login-reponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponseDto implements Serializable{

	private String token;	
	private long id;
	private UtilisateurDto user;
	
	
	public UtilisateurDto getUser() {
		return user;
	}

	public void setUser(UtilisateurDto user) {
		this.user = user;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LoginResponseDto() {
		
	}

	public LoginResponseDto(String token, long id, UtilisateurDto user) {
		super();
		this.token = token;
		this.id = id;
		this.user = user;
	}

	public LoginResponseDto(String token, long id) {
		this.token = token;
		this.id = id;

	}

	public long getId() {
		return id;
	}


	public LoginResponseDto(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

