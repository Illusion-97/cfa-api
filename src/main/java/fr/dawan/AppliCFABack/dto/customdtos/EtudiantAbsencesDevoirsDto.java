package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.dto.AbsenceDto;
import fr.dawan.AppliCFABack.dto.DevoirEtudiantDto;
import fr.dawan.AppliCFABack.dto.NiveauDto;
import fr.dawan.AppliCFABack.dto.PositionnementDto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
/**
 * @author Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 */
public class EtudiantAbsencesDevoirsDto implements Serializable {

	private EtudiantInfoInterventionDto etudiant;
		
	private List<AbsenceDto> absences;
	
	private List<DevoirEtudiantDto> devoirs;
	
	private PositionnementDto positionnement;

	private NiveauDto niveauDebut;
	
	private NiveauDto niveauFin;
	
	
	/**
	 * @return le positionnement
	 */
	public PositionnementDto getPositionnement() {
		return positionnement;
	}

	/**
	 * @param positionnement le positionnement à affecter
	 
	 */
	public void setPositionnement(PositionnementDto positionnement) {
		this.positionnement = positionnement;
	}

	/**
	 * @return le niveauDebut
	 */
	public NiveauDto getNiveauDebut() {
		return niveauDebut;
	}

	/**
	 * @param niveauDebut le niveauDebut à affecter
	 
	 */
	public void setNiveauDebut(NiveauDto niveauDebut) {
		this.niveauDebut = niveauDebut;
	}

	/**
	 * @return le niveauFin
	 */
	public NiveauDto getNiveauFin() {
		return niveauFin;
	}

	/**
	 * @param niveauFin le niveauFin à affecter
	 
	 */
	public void setNiveauFin(NiveauDto niveauFin) {
		this.niveauFin = niveauFin;
	}

	/**
	 * @return le etudiant
	 */
	public EtudiantInfoInterventionDto getEtudiant() {
		return etudiant;
	}

	/**
	 * @param etudiant le etudiant à affecter
	 
	 */
	public void setEtudiant(EtudiantInfoInterventionDto etudiant) {
		this.etudiant = etudiant;
	}

	/**
	 * @return les absences
	 */
	public List<AbsenceDto> getAbsences() {
		return absences;
	}

	/**
	 * @param absences les absences à affecter
	 
	 */
	public void setAbsences(List<AbsenceDto> absences) {
		this.absences = absences;
	}

	/**
	 * @return le devoirs
	 */
	public List<DevoirEtudiantDto> getDevoirs() {
		return devoirs;
	}

	/**
	 * @param devoirs le devoirs à affecter
	 
	 */
	public void setDevoirs(List<DevoirEtudiantDto> devoirs) {
		this.devoirs = devoirs;
	}

	/**
	 * @return les devoirs
	 */
	


	
}
