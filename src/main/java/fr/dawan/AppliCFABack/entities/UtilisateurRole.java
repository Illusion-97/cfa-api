package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class UtilisateurRole extends BaseEntity implements Serializable {

	@Column(nullable = false, length = 255)
	private String intitule;

	@ManyToMany(mappedBy = "roles")
	List<Utilisateur> utilisateurs;

	public UtilisateurRole() {
		super();
	}

	public UtilisateurRole(String intitule, List<Utilisateur> utilisateurs) {
		super();
		this.intitule = intitule;
		this.utilisateurs = utilisateurs;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
