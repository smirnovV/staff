package ru.smirnovv.service;

import org.springframework.data.domain.Pageable;
import ru.smirnovv.entity.Organization;

import java.util.List;

public interface StaffService {
    void create(String name, Long bossId, Long organizationId);

    void delete(long id);

    void update(long id, String name, Long bossId, Long organizationId);

    List<Organization> getPage(Pageable pageable);

}
