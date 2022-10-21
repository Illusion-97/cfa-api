package fr.dawan.AppliCFABack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
public class Positionnement extends BaseEntity implements Serializable {

	@Min(0)
	@Max(5)
	@Enumerated(EnumType.ORDINAL)
	private Niveau niveauDebut;
	
	@Min(0)
	@Max(5)
	@Enumerated(EnumType.ORDINAL)
	private Niveau niveauFin;
	@ManyToOne
	private Intervention intervention;

	@ManyToOne
	private Etudiant etudiant;

	public enum Niveau {
		ABSENT(0, "#000000", "Absent"), AUCUNECONNAISSANCE(1, "#7E0021", "Aucune connaissance"),
		NOTIONS(2, "#FF3300", "Notions"), ENCOURSDACQUISITION(3, "#FFCC00", "En cours d'acquisition"),
		ACQUIS(4, "#FFFF66", "Acquis"), NIVEAUAVANCE(5, "#00FF66", "Niveau avancé");

		private int valeur;
		
		private String codeCouleur;
		
		private String description;


		Niveau() {
		}


		Niveau(int valeur, String codeCouleur, String description) {
			this.valeur = valeur;
			this.codeCouleur = codeCouleur;
			this.description = description;
		}


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
		 * @return le description
		 */
		public String getDescription() {
			return description;
		}


		/**
		 * @param description le description à affecter
		 
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
	}



	public Niveau getNiveauDebut() {
		return niveauDebut;
	}

	public void setNiveauDebut(Niveau niveauDebut) {
		this.niveauDebut = niveauDebut;
	}

	public Niveau getNiveauFin() {
		return niveauFin;
	}

	public void setNiveauFin(Niveau niveauFin) {
		this.niveauFin = niveauFin;
	}

	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

}
