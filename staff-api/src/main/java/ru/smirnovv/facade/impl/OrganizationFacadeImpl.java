package ru.smirnovv.facade.impl;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.controller.dto.PageInfo;
import ru.smirnovv.controller.dto.converter.Mapper;
import ru.smirnovv.facade.OrganizationFacade;
import ru.smirnovv.service.OrganizationService;

import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationFacadeImpl implements OrganizationFacade {

    private final Mapper mapper;
    private final OrganizationService organizationService;

    @Override
    public void create(String name, Long headOrganizationId) {
        organizationService.create(name, headOrganizationId);
    }

    @Override
    public void delete(long id) {
        organizationService.delete(id);
    }

    @Override
    public void update(long id, String name, Long headOrganizationId) {
        organizationService.update(id, name, headOrganizationId);
    }

    @Override
    public List<OrganizationDTO> getPage(PageInfo pageInfo) {
        return mapper.convert(organizationService.getPage(mapper.convert(pageInfo, Pageable.class)),
                              OrganizationDTO.class);
    }

    @Override
    public List<OrganizationDTO> getAll() {
        return null;
    }

}
