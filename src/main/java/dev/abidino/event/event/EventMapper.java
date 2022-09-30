package dev.abidino.event.event;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toEvent(EventDto eventDto);

    List<Event> toEvent(List<EventDto> eventDto);

    EventDto toEventDto(Event event);

    List<EventDto> toEventDto(List<Event> event);
}
