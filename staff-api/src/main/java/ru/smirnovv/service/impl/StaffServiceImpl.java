package ru.smirnovv.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.repository.StaffRepository;
import ru.smirnovv.service.StaffService;

import java.util.List;

@Lazy
@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void create(String name, Long bossId, Long organizationId) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(long id, String name, Long bossId, Long organizationId) {

    }

    @Override
    public List<Organization> getPage(Pageable pageable) {
        return null;
    }
}
