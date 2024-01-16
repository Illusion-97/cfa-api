package fr.dawan.AppliCFABack.entities.examen;

import fr.dawan.AppliCFABack.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modules extends BaseEntity {
    private String name;
    private LocalTime duration;
}
