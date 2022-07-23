package ru.smirnovv.service;

import org.springframework.data.domain.Pageable;
import ru.smirnovv.entity.Staff;

import java.util.List;

public interface StaffService {

    void create(String name, Long bossId, Long organizationId);

    void delete(long id);

    void update(long id, String name, Long bossId, Long organizationId);

    List<Staff> getPage(Pageable pageable);

}
