package dev.abidino.event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Long startDate;

    Long endDate;

    Long organizationId;

    @ElementCollection
    List<String> participants = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    EventType eventType;

    EventDto toEventDto() {
        return new EventDto(this.id, this.name, this.startDate, this.endDate, this.organizationId, this.participants, this.eventType);
    }
}
