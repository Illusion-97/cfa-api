package fr.dawan.AppliCFABack.dto;

import java.io.Serializable;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Count Entity
 */
@SuppressWarnings("serial")
public class CountDto implements Serializable {

	private long nb;

	public CountDto() {

	}

	public CountDto(long nb) {
		this.nb = nb;
	}

	public long getNb() {
		return nb;
	}

	public void setNb(long nb) {
		this.nb = nb;
	}

}
