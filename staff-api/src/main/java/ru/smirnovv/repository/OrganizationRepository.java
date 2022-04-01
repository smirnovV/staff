package ru.smirnovv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.smirnovv.entity.Organization;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    /**
     * Подсчитать количество дочерних организации.
     *
     * @param headOrganizationId УИД головной организации
     * @return количество дочерних организации
     */
    long countAllByHeadOrganizationId(Long headOrganizationId);


    /**
     * Найти все дочерние организации.
     *
     * @param headOrganizationIds УИД головной организации
     * @return список дочерних организации
     */
    List<Organization> findOrganizationsByHeadOrganizationIdIn(List<Long> headOrganizationIds);

}
