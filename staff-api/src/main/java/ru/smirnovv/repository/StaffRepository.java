package ru.smirnovv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smirnovv.entity.Staff;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    /**
     * Подсчитать количество подчиненных
     *
     * @param bossId УИД начальника
     * @return количество подчиненных
     */
    long countAllByBossId(Long bossId);


    /**
     * Найти всех подчиненных
     *
     * @param bossIds УИД-ы начальников
     * @return список подчиненных
     */
    List<Staff> findOrganizationsByBossIdIn(List<Long> bossIds);

}
