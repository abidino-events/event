package dev.abidino.event.event;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public record EventController(EventService eventService, EventMapper eventMapper) {

    @PostMapping
    EventDto save(@RequestBody EventDto eventDto, @RequestParam Long organizationId) {
        Event event = eventMapper.toEvent(eventDto);
        Event savedEvent = eventService.saveWithOrganizationId(event, organizationId);
        return eventMapper.toEventDto(savedEvent);
    }

    @GetMapping("/{id}")
    EventDto get(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return eventMapper.toEventDto(event);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

    @PatchMapping("{id}/join")
    EventDto join(@PathVariable Long id) {
        Event event = eventService.join(id);
        return eventMapper.toEventDto(event);
    }

    @GetMapping("/all")
    List<EventDto> allByOrganizationId(@RequestParam Long organizationId, @RequestParam(required = false) EventType eventType) {
        List<Event> eventList = eventService.findAllByOrganizationIdAndEventType(organizationId, eventType);
        return eventMapper.toEventDto(eventList);
    }
}
