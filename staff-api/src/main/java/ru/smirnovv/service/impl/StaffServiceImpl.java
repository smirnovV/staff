package ru.smirnovv.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.smirnovv.entity.Staff;
import ru.smirnovv.exception.organization.DeletionHeadOrganizationException;
import ru.smirnovv.exception.organization.UpdateOrganizationException;
import ru.smirnovv.exception.staff.StaffNotFoundException;
import ru.smirnovv.repository.StaffRepository;
import ru.smirnovv.service.OrganizationService;
import ru.smirnovv.service.StaffService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Lazy
@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final OrganizationService organizationService;

    @Override
    public void create(String name, Long bossId, Long organizationId) {
        Staff staff = new Staff();
        staff.setName(name);

        if (!Objects.isNull(bossId)) {
            staff.setBoss(getStaff(bossId));
        }

        if (!Objects.isNull(organizationId)) {
            staff.setOrganization(organizationService.getOrganization(organizationId));
        }


    }

    private Staff getStaff(Long id) {
        return staffRepository.findById(id)
                              .orElseThrow(() -> {throw new StaffNotFoundException("");});
    }

    @Override
    public void delete(long id) {
        if (staffRepository.countAllByBossId(id) != 0) {
            throw new DeletionHeadOrganizationException();
        }
        staffRepository.deleteById(id);
    }

    @Override
    public void update(long id, String name, Long bossId, Long organizationId) {
        Staff staff = getStaff(id);
        staff.setName(name);

        staff.setOrganization(organizationService.getOrganization(organizationId));

        Staff boss = isNull(bossId) ? null : getStaff(bossId);

        if (!isNull(bossId)) {
            validateTree(staff, boss);
        }

        staff.setBoss(boss);

        staffRepository.save(staff);
    }

    @Override
    public List<Staff> getPage(Pageable pageable) {
        return staffRepository.findAll(pageable)
                              .toList();
    }

    private void validateTree(Staff staff, Staff boss) {
        List<Long> subordinates = new ArrayList<>();
        subordinates.add(staff.getId());

        while (!subordinates.isEmpty()) {
            if (subordinates.contains(boss.getId())) {
                throw new UpdateOrganizationException("");
            }

            List<Long> newSubsidiaries = staffRepository.findOrganizationsByBossIdIn(subordinates)
                                                        .stream()
                                                        .map(Staff::getId)
                                                        .collect(Collectors.toList());
            subordinates.clear();
            subordinates.addAll(newSubsidiaries);
        }
    }

}
