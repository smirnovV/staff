package ru.smirnovv.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.exception.DeletionHeadOrganizationException;
import ru.smirnovv.exception.UpdateOrganizationException;
import ru.smirnovv.repository.OrganizationRepository;
import ru.smirnovv.service.OrganizationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static ru.smirnovv.utils.TestUtils.setPrivateId;

/**
 * Тесты для  {@link OrganizationService}
 */
@DisplayName("Тесты сервиса работы со сведениями об организации")
class OrganizationServiceImplTest {

    private static final OrganizationRepository organizationRepository = Mockito.mock(OrganizationRepository.class);

    private static final OrganizationService organizationService = new OrganizationServiceImpl(organizationRepository);

    @Test
    @DisplayName("При созданий организации не должно выбрасываться исключения")
    public void creationOrganizationShouldNotThrowException() {
        when(organizationRepository.getById(anyLong())).thenReturn(new Organization());

        Assertions.assertDoesNotThrow(() -> organizationService.create("name", 1L));
    }

    @Test
    @DisplayName("При удалении организации должно выбрасываться исключение, если организация является головной")
    public void deletionOrganizationShouldThrowException() {
        when(organizationRepository.countAllByHeadOrganizationId(anyLong())).thenReturn(1L);

        Assertions.assertThrows(DeletionHeadOrganizationException.class, () -> organizationService.delete(1));
    }

    @Test
    @DisplayName("При удалении организации не должно выбрасываться исключение")
    public void deletionOrganizationShouldNotThrowException() {
        when(organizationRepository.countAllByHeadOrganizationId(anyLong())).thenReturn(0L);

        Assertions.assertDoesNotThrow(() -> organizationService.delete(1));
    }

    @Test
    @DisplayName("При удалении организации должно выбрасываться исключение, если организация является головной")
    public void updatingOrganizationShouldThrowException() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization();
        setPrivateId(organization, 2L);
        Organization childOrganization = new Organization();
        setPrivateId(childOrganization, 1L);
        childOrganization.setHeadOrganization(organization);

        when(organizationRepository.findOrganizationsByHeadOrganizationIdIn(anyList()))
                .thenReturn(List.of(childOrganization));
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization))
                                                        .thenReturn(Optional.of(childOrganization));

        Assertions.assertThrows(UpdateOrganizationException.class, () -> organizationService.update(2L, "name", 1L));
    }

    @Test
    @DisplayName("При удалении организации должно выбрасываться исключение, если организация является головной")
    public void updatingOrganizationShouldNotThrowException() throws NoSuchFieldException, IllegalAccessException {
        Organization organization = new Organization();
        setPrivateId(organization, 2L);
        Organization childOrganization = new Organization();
        setPrivateId(childOrganization, 1L);

        when(organizationRepository.findOrganizationsByHeadOrganizationIdIn(anyList()))
                .thenReturn(Collections.emptyList());
        when(organizationRepository.findById(anyLong())).thenReturn(Optional.of(organization))
                                                        .thenReturn(Optional.of(childOrganization));

        Assertions.assertDoesNotThrow(() -> organizationService.update(2L, "name", 1L));
    }

}