package ru.smirnovv.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.exception.organization.OrganizationNotFoundException;
import ru.smirnovv.exception.organization.UpdateOrganizationException;
import ru.smirnovv.exception.staff.DeletionBossStaffException;
import ru.smirnovv.repository.OrganizationRepository;
import ru.smirnovv.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Lazy
@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public void create(String name, Long headOrganizationId) {
        Organization organization = new Organization();
        organization.setName(name);

        if (!isNull(headOrganizationId)) {
            Organization headOrganization = getOrganization(headOrganizationId);
            organization.setHeadOrganization(headOrganization);
        }

        organizationRepository.save(organization);
    }

    @Override
    public void delete(long id) {
        if (organizationRepository.countAllByHeadOrganizationId(id) != 0) {
            throw new DeletionBossStaffException("");
        }
        organizationRepository.deleteById(id);
    }

    @Override
    public void update(long id, String name, Long headOrganizationId) {
        Organization organization = getOrganization(id);
        organization.setName(name);

        Organization headOrganization = isNull(headOrganizationId) ? null : getOrganization(headOrganizationId);

        if (!isNull(headOrganizationId)) {
            validateTree(organization, headOrganization);
        }

        organization.setHeadOrganization(headOrganization);

        organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getPage(Pageable pageable) {
        return organizationRepository.findAll(pageable).toList();
    }

    @Override
    public Organization getOrganization(Long id) {
        return organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException(id));
    }

    private void validateTree(Organization organization, Organization headOrganization) {
        List<Long> subsidiaries = new ArrayList<>();
        subsidiaries.add(organization.getId());

        while (!subsidiaries.isEmpty()) {
            if (subsidiaries.contains(headOrganization.getId())) {
                throw new UpdateOrganizationException("");
            }

            List<Long> newSubsidiaries =
                    organizationRepository.findOrganizationsByHeadOrganizationIdIn(subsidiaries).stream()
                                          .map(Organization::getId).collect(Collectors.toList());
            subsidiaries.clear();
            subsidiaries.addAll(newSubsidiaries);
        }
    }

}
