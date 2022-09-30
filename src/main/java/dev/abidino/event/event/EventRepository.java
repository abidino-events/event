package dev.abidino.event.event;

import dev.abidino.event.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrganization(Organization organization);
    List<Event> findAllByOrganizationAndEventType(Organization organization, EventType eventType);
}
