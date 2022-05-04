package fr.dawan.AppliCFABack.dto;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-réinitialisation du mot de passe Entity
 */
public class ResetPasswordDto {
	private String email;

	public ResetPasswordDto(String email) {
		super();
		this.email = email;
	}

	public ResetPasswordDto() {
		super();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
