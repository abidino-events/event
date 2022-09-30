package dev.abidino.event.organization;

import dev.abidino.event.exception.BadRequestException;
import dev.abidino.event.user.User;
import dev.abidino.event.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record OrganizationService(OrganizationRepository organizationRepository,
                                  UserService userService) {

    public Organization save(Organization organization) {
        User authenticateUser = userService.authenticateUser();
        organization.getAdminList().add(authenticateUser);
        organization.getMemberList().add(authenticateUser);
        return organizationRepository.save(organization);
    }

    public Organization findById(Long id) {
        return organizationRepository.findById(id).orElseThrow(() -> new BadRequestException("Organization not found"));
    }

    public void delete(Long id) {
        Organization organization = findById(id);
        User authenticateUser = userService.authenticateUser();

        if (organization.getAdminList().contains(authenticateUser)) {
            organization.setStatus(Status.PASSIVE);
            organizationRepository.save(organization);
            return;
        }
        throw new BadRequestException("Bu islem icin gerekli yetkiye sahip degilsiniz");
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public boolean isOrganizationAdmin(Organization organization) {
        return organization.getAdminList().stream().anyMatch(user -> user.equals(userService.authenticateUser()));
    }

    public boolean isOrganizationMember(Organization organization) {
        return organization.getMemberList().stream().anyMatch(user -> user.equals(userService.authenticateUser()));
    }
}
