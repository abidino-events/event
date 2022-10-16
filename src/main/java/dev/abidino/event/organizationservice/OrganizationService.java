package dev.abidino.event.organizationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${client.organization.value}", url = "${client.organization.url}")
public interface OrganizationService {

    @GetMapping("/{id}/user-role")
    UserRole getAuthUserRoleInOrganization(@PathVariable Long id);
}
