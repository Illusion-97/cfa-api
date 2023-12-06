package fr.dawan.AppliCFABack.entities;

import javax.persistence.*;
import java.io.Serializable;

/***
 * @author Feres BG.
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

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseEntity(long id, int version) {
		super();
		this.id = id;
		this.version = version;
	}

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
