package dev.abidino.event.organization;

import dev.abidino.event.user.UserDto;

import java.util.List;

public record OrganizationDto(Long id,
                              String orgName,
                              List<UserDto> adminList,
                              List<UserDto> memberList) {
}
