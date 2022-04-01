package ru.smirnovv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smirnovv.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
