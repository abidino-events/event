package dev.abidino.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrganizationId(Long organizationId);

    List<Event> findAllByOrganizationIdAndEventType(Long organizationId, EventType eventType);
}
