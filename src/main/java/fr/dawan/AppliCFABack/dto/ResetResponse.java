package fr.dawan.AppliCFABack.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement(name = "login-reponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResetResponse implements Serializable {

	private String token;
	private String password;
	
	public ResetResponse(String token, String password) {
		this.token = token;
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
        return "ResetResponse [token=" + token + ", password=" + password + "]";
	}



}
