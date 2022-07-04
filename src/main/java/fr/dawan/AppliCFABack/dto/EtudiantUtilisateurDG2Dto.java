package fr.dawan.AppliCFABack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class EtudiantUtilisateurDG2Dto implements Serializable {
    @JsonProperty(value = "registration_id")
    private long registrationId;

    @JsonProperty(value = "person_id")
    private long personId;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "last_name")
    private String lastName;

    private String email;

    private int version;

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
