package dev.abidino.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public record EventController(EventService eventService) {

    @PostMapping
    EventDto save(@RequestBody EventDto eventDto, @RequestParam Long organizationId) {
        Event savedEvent = eventService.saveWithOrganizationId(eventDto.toEvent(), organizationId);
        return savedEvent.toEventDto();
    }

    @GetMapping("/{id}")
    EventDto get(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return event.toEventDto();
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PatchMapping("{id}/join")
    EventDto join(@PathVariable Long id) {
        Event event = eventService.join(id);
        return event.toEventDto();
    }

    @GetMapping("/all")
    List<EventDto> allByOrganizationId(@RequestParam Long organizationId, @RequestParam(required = false) EventType eventType) {
        List<Event> eventList = eventService.findAllByOrganizationIdAndEventType(organizationId, eventType);
        return eventList.stream().map(Event::toEventDto).collect(Collectors.toList());
    }
}
