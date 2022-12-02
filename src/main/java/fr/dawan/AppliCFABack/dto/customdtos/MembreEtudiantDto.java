package fr.dawan.AppliCFABack.dto.customdtos;

import java.io.Serializable;
import java.util.stream.Stream;

public class MembreEtudiantDto implements Serializable {

    private String membreNom;
    private String membrePrenom;
    private Stream<String> membreRole;

    public MembreEtudiantDto(String membreNom, String membrePrenom, Stream<String> membreRole) {
        this.membreNom = membreNom;
        this.membrePrenom = membrePrenom;
        this.membreRole = membreRole;
    }

    public String getMembreNom() {
        return membreNom;
    }

    public void setMembreNom(String membreNom) {
        this.membreNom = membreNom;
    }

    public String getMembrePrenom() {
        return membrePrenom;
    }

    public void setMembrePrenom(String membrePrenom) {
        this.membrePrenom = membrePrenom;
    }

    public Stream<String> getMembreRole() {
        return membreRole;
    }

    public void setMembreRole(Stream<String> membreRole) {
        this.membreRole = membreRole;
    }
}
