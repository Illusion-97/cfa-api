package fr.dawan.AppliCFABack.dto.customdtos;

import fr.dawan.AppliCFABack.entities.examen.Module;
import lombok.Value;

import java.io.Serializable;
import java.time.Duration;

/**
 * DTO for {@link Module}
 */
@Value
public class ModuleDto implements Serializable {
    long id;
    int version;
    String name;
    Duration duration;
}
