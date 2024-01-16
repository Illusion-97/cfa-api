package fr.dawan.AppliCFABack.entities.examen;

import fr.dawan.AppliCFABack.entities.BaseEntity;
import fr.dawan.AppliCFABack.entities.Cursus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SoutenanceModel extends BaseEntity {

    @OneToOne
    private Cursus cursus;
    @OneToMany
    private List<Modules> modules = new ArrayList<>();

    public static SoutenanceModel getBaseModel(Cursus cursus) {
        return new SoutenanceModel().setCursus(cursus);
    }
}
