package dev.abidino.event.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.abidino.event.organization.OrganizationDto;
import dev.abidino.event.user.UserDto;

import java.util.List;

public record EventDto(Long id,
                       String name,
                       long startDate,
                       long endDate,

                       @JsonIgnore
                       OrganizationDto organizationDto,

                       List<UserDto> participants,
                       EventType eventType) {
}
