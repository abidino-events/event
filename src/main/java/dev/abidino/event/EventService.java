package dev.abidino.event;

import dev.abidino.event.exception.BadRequestException;
import dev.abidino.event.organizationservice.OrganizationService;
import dev.abidino.event.organizationservice.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public record EventService(EventRepository eventRepository, OrganizationService organizationService) {

    public void delete(Long id) {
        Event event = findById(id);
        eventRepository.delete(event);
    }

    public Event saveWithOrganizationId(Event event, Long organizationId) {
        UserRole userRole = organizationService.getAuthUserRoleInOrganization(organizationId);
        if (userRole != UserRole.ADMIN) {
            throw new BadRequestException("Bu islemi yapmaya yetkiniz yoktur");
        }
        event.organizationId = organizationId;
        return eventRepository.save(event);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new BadRequestException("Etkinlik bulunamadi"));
    }

    public List<Event> findAllByOrganizationIdAndEventType(Long organizationId, EventType eventType) {
        if (Objects.isNull(eventType)) {
            return eventRepository.findAllByOrganizationId(organizationId);
        }
        return eventRepository.findAllByOrganizationIdAndEventType(organizationId, eventType);
    }

    public Event join(Long id) {
        Event event = findById(id);
        UserRole userRole = organizationService.getAuthUserRoleInOrganization(event.organizationId);

        if (event.eventType == EventType.PRIVATE && userRole == UserRole.UNAUTHORIZED) {
            throw new BadRequestException("Bu etkinlige sadece organizasyondaki üyeler katilabilir!");
        }

        String authenticateUser = getAuthenticateUser();
        event.participants.add(authenticateUser);
        return eventRepository.save(event);
    }

    String getAuthenticateUser() {
        //TODO : @abidino user bilgilerini aldiginda burayi degistir
        return "user";
    }


}
