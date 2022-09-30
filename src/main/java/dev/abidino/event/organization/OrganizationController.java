package dev.abidino.event.organization;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
record OrganizationController(OrganizationService organizationService, OrganizationMapper organizationMapper) {

    @PostMapping
    OrganizationDto save(@RequestBody OrganizationDto organizationDto) {
        Organization organization = organizationMapper.ToOrganization(organizationDto);
        Organization savedOrganization = organizationService.save(organization);
        return organizationMapper.ToOrganizationDto(savedOrganization);
    }

    @GetMapping("/{id}")
    OrganizationDto get(@PathVariable Long id) {
        Organization organization = organizationService.findById(id);
        return organizationMapper.ToOrganizationDto(organization);
    }

    @GetMapping("/all")
    List<OrganizationDto> all() {
        List<Organization> organizationList = organizationService.findAll();
        return organizationMapper.ToOrganizationDto(organizationList);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        organizationService.delete(id);
    }

}
