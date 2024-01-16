package fr.dawan.AppliCFABack.entities.examen;

import fr.dawan.AppliCFABack.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Module extends BaseEntity {
    private String name;
    private Duration duration;

    public static Module getModuleExample() {
        return new Module().setName("Example").setDuration(Duration.ofMinutes(15));
    }
}
