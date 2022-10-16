package dev.abidino.event;

import java.util.List;

public record EventDto(Long id,
                       String name,
                       long startDate,
                       long endDate,

                       Long organizationId,

                       List<String> participants,
                       EventType eventType) {

    Event toEvent() {
        Event event = new Event();
        event.id = this.id;
        event.name = this.name;
        event.startDate = this.startDate;
        event.endDate = this.endDate;
        event.organizationId = this.organizationId;
        event.participants = this.participants;
        event.eventType = this.eventType;
        return event;
    }
}
