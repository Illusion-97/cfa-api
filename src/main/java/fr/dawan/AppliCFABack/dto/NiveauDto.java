package fr.dawan.AppliCFABack.dto;

public class NiveauDto {

	private int valeur;
	
	private String codeCouleur;
	
	private String description;

	/**
	 * @return le valeur
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * @param valeur le valeur à affecter
	 
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * @return le codeCouleur
	 */
	public String getCodeCouleur() {
		return codeCouleur;
	}

	/**
	 * @param codeCouleur le codeCouleur à affecter
	 
	 */
	public void setCodeCouleur(String codeCouleur) {
		this.codeCouleur = codeCouleur;
	}

	/**
	 * @return le descreption
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param descreption le descreption à affecter
	 
	 */
	public void setDescription(String descreption) {
		this.description = descreption;
	}
	
	
}
