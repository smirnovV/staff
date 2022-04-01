package ru.smirnovv.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.exception.DeleteHeadOrganizationException;
import ru.smirnovv.exception.OrganizationCreationException;
import ru.smirnovv.exception.OrganizationNotFoundException;
import ru.smirnovv.repository.OrganizationRepository;
import ru.smirnovv.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Lazy
@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public void create(String name, Long headOrganizationId) {
        Organization organization = new Organization();
        organization.setName(name);

        if (!Objects.isNull(headOrganizationId)) {
            Organization headOrganization = organizationRepository.getById(headOrganizationId);
            organization.setHeadOrganization(headOrganization);
        }

        organizationRepository.save(organization);
    }

    @Override
    public void delete(long id) {
        if (organizationRepository.countAllByHeadOrganizationId(id) != 0) {
            throw new DeleteHeadOrganizationException();
        }
        organizationRepository.deleteById(id);
    }

    @Override
    public void update(long id, String name, Long headOrganizationId) {
        Organization headOrganization = organizationRepository.getById(headOrganizationId);

        Organization organization = organizationRepository.findById(id)
                                                          .orElseThrow(OrganizationNotFoundException::new);
        organization.setName(name);
        organization.setHeadOrganization(headOrganization);

        validateTree(organization, headOrganization);

        organizationRepository.save(organization);
    }

    @Override
    public List<Organization> getPage(Pageable pageable) {
        return organizationRepository.findAll(pageable)
                                     .toList();
    }

    private void validateTree(Organization organization, Organization headOrganization) {
        List<Long> subsidiaries = new ArrayList<>();
        subsidiaries.add(organization.getId());

        while (!subsidiaries.isEmpty()) {
            if (subsidiaries.contains(headOrganization.getId())) {
                throw new OrganizationCreationException();
            }

            List<Long> newSubsidiaries = organizationRepository.findOrganizationsByHeadOrganizationIdIn(subsidiaries)
                                                               .stream()
                                                               .map(Organization::getId)
                                                               .collect(Collectors.toList());
            subsidiaries.clear();
            subsidiaries.addAll(newSubsidiaries);
        }
    }
}
