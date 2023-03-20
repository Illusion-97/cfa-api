package fr.dawan.AppliCFABack.dto.customdtos.dossierprojet;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GetDossierProjetEtudiant implements Serializable{

	private long id;
	private List<GetDossierProjetDto> getDossierProjetDto;
	private int version;
	
	 public List<GetDossierProjetDto> getDossierProfessionnel() {
	        return getDossierProjetDto;
	    }

	    public void setDossierProfessionnel(List<GetDossierProjetDto> getDossierProjetDto) {
	        this.getDossierProjetDto = getDossierProjetDto;
	    }

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
	
}
