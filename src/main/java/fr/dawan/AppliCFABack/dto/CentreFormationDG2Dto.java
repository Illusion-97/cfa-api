package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
/**
 * 
 * 
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO de l'appel API DG2
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CentreFormationDG2Dto extends BaseEntityDto implements Serializable{

	private String name;
	private boolean published;
	private String country;
	private String address;
	private String zipCode;
	private String city;

	
	public CentreFormationDG2Dto() {
		super();
	}


	public CentreFormationDG2Dto(String name, boolean published, String country, String address, String zipCode,
			String city) {
		super();
		this.name = name;
		this.published = published;
		this.country = country;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return le address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address le address à affecter
	 
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return le zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode le zipCode à affecter
	 
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return le city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city le city à affecter
	 
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
