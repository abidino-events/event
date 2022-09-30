package dev.abidino.event.event;

import dev.abidino.event.exception.BadRequestException;
import dev.abidino.event.organization.Organization;
import dev.abidino.event.organization.OrganizationService;
import dev.abidino.event.user.User;
import dev.abidino.event.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record EventService(EventRepository eventRepository,
                           OrganizationService organizationService,
                           UserService userService) {

    public void delete(Long id) {
        Event event = findById(id);
        eventRepository.delete(event);
    }

    public Event saveWithOrganizationId(Event event, Long organizationId) {
        Organization organization = organizationService.findById(organizationId);
        boolean isAdminUser = organizationService.isOrganizationAdmin(organization);
        if (!isAdminUser) {
            throw new BadRequestException("Bu islemi yapmaya yetkiniz yoktur");
        }
        event.setOrganization(organization);
        return eventRepository.save(event);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new BadRequestException("Etkinlik bulunamadi"));
    }

    public List<Event> findAllByOrganizationIdAndEventType(Long organizationId, EventType eventType) {
        Organization organization = organizationService.findById(organizationId);
        if (Objects.isNull(eventType)) {
            return eventRepository.findAllByOrganization(organization);
        }
        return eventRepository.findAllByOrganizationAndEventType(organization, eventType);
    }

    public Event join(Long id) {
        Event event = findById(id);
        boolean isOrganizationMember = organizationService.isOrganizationMember(event.getOrganization());
        if (event.getEventType() == EventType.PRIVATE && !isOrganizationMember) {
            throw new BadRequestException("Bu etkinlige sadece organizasyondaki üyeler katilabilir!");
        }
        User authenticateUser = userService.authenticateUser();
        event.getParticipants().add(authenticateUser);
        return eventRepository.save(event);
    }
}
