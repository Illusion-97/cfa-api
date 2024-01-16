package fr.dawan.AppliCFABack.event.cursus;

import fr.dawan.AppliCFABack.entities.Cursus;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CursusCreatedEvent extends ApplicationEvent {
    private final Cursus created;
    public CursusCreatedEvent(Object source, Cursus created) {
        super(source);
        this.created = created;
    }
}
