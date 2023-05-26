package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.ExamenDto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return DTO-Competence professionnelle Entity
 */
@SuppressWarnings("serial")
public class CompetenceCouvertesDossierProjetDto extends BaseEntityDto implements Serializable {

	private String libelle;
	private byte numeroFiche;
	private List<ExamenDto> examensDto;
	private long activiteTypeId;
	private long dossierProjetId;
    
	public CompetenceCouvertesDossierProjetDto() {
		super();
	}

	/**
	 * @param libelle
	 * @param numeroFiche
	 * @param examensDto
	 * @param activiteTypeId
	 */
	public CompetenceCouvertesDossierProjetDto(long id, String libelle, byte numeroFiche, long activiteTypeId, long dossierProjetId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.numeroFiche = numeroFiche;
		this.activiteTypeId = activiteTypeId;
		this.dossierProjetId = dossierProjetId;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the numeroFiche
	 */
	public byte getNumeroFiche() {
		return numeroFiche;
	}

	/**
	 * @param numeroFiche the numeroFiche to set
	 */
	public void setNumeroFiche(byte numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	/**
	 * @return the examensDto
	 */
	public List<ExamenDto> getExamensDto() {
		return examensDto;
	}

	/**
	 * @param examensDto the examensDto to set
	 */
	public void setExamensDto(List<ExamenDto> examensDto) {
		this.examensDto = examensDto;
	}

	/**
	 * @return the activiteTypeId
	 */
	public long getActiviteTypeId() {
		return activiteTypeId;
	}

	/**
	 * @param activiteTypeId the activiteTypeId to set
	 */
	public void setActiviteTypeId(long activiteTypeId) {
		this.activiteTypeId = activiteTypeId;
	}

	public long getDossierProjetId() {
		return dossierProjetId;
	}

	public void setDossierProjetId(long dossierProjetId) {
		this.dossierProjetId = dossierProjetId;
	}

}
