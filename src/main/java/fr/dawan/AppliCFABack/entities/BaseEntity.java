package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/***
 * 
 * @author Feres BG,Valentin C.
 * @see fr.dawan.appliCfaBack.entities
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;

	@Version
	protected int version;

	/**
	 * @return l'id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id l'id à affecter
	 * 
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return la version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version la version à affecter
	 * 
	 */
	public void setVersion(int version) {
		this.version = version;
	}

}
