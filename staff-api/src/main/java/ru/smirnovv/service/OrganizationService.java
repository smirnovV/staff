package ru.smirnovv.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.smirnovv.entity.Organization;

import java.util.List;

public interface OrganizationService {

    void create(String name, Long headOrganizationId);

    void delete(long id);

    void update(long id, String name, Long headOrganizationId);

    List<Organization> getPage(Pageable pageable);
}
