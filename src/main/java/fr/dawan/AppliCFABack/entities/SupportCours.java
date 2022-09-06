package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class SupportCours extends BaseEntity implements Serializable {

	@Column(nullable = false)
	private String titre;

	@Column(nullable = false)
	private String pieceJointe;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Intervention> interventions;

	public List<Long> getInterventionsId() {
		List<Long> res = new ArrayList<>();
		for (Intervention i : interventions) {
			res.add(i.getId());
		}
		return res;
	}

	public List<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(List<Intervention> interventions) {
		this.interventions = interventions;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getPieceJointe() {
		return pieceJointe;
	}

	public void setPieceJointe(String pieceJointe) {
		this.pieceJointe = pieceJointe;
	}

}
