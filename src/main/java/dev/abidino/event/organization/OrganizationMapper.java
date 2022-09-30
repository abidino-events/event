package dev.abidino.event.organization;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface OrganizationMapper {
    Organization ToOrganization(OrganizationDto organizationDto);

    List<Organization> ToOrganization(List<OrganizationDto> userDto);

    OrganizationDto ToOrganizationDto(Organization user);

    List<OrganizationDto> ToOrganizationDto(List<Organization> user);
}
