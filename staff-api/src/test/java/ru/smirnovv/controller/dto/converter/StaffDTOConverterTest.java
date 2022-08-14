package ru.smirnovv.controller.dto.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.smirnovv.controller.dto.StaffDTO;
import ru.smirnovv.entity.Organization;
import ru.smirnovv.entity.Staff;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.smirnovv.utils.Utils.setId;

/**
 * Тесты {@link StaffDTOConverter}
 */
@DisplayName("Тест преобразователя сущности сотрудника")
class StaffDTOConverterTest {

    StaffDTOConverter converter = new StaffDTOConverter();
    Mapper mapper = new Mapper(List.of(converter));

    @Test
    @DisplayName("Тест преобразования объекта с информации о сотруднике")
    void shouldCorrectConvertEntity() {
        Organization head = setId(new Organization(), 2L);
        head.setName("head");

        Organization organization = setId(new Organization(), 1L);
        organization.setName("organization");
        organization.setHeadOrganization(head);

        Staff boss = setId(new Staff(), 3L);
        boss.setOrganization(organization);
        boss.setName("boss");
        boss.setBoss(new Staff());

        Staff staff = setId(new Staff(), 4L);
        staff.setName("staff");
        staff.setBoss(boss);
        staff.setOrganization(organization);

        StaffDTO dto = mapper.convert(staff, StaffDTO.class);

        assertAll(() -> assertEquals("organization",
                                     dto.getOrganization()
                                        .getName()),
                  () -> assertEquals("head",
                                     dto.getOrganization()
                                        .getHeadOrganization()
                                        .getName()),
                  () -> assertNull(dto.getOrganization()
                                      .getHeadOrganization()
                                      .getHeadOrganization()),
                  () -> assertEquals("organization",
                                     dto.getBoss()
                                        .getOrganization()
                                        .getName()),
                  () -> assertEquals("head",
                                     dto.getBoss()
                                        .getOrganization()
                                        .getHeadOrganization()
                                        .getName()),
                  () -> assertNull(dto.getBoss()
                                      .getOrganization()
                                      .getHeadOrganization()
                                      .getHeadOrganization()),
                  () -> assertEquals("staff", dto.getName()),
                  () -> assertEquals(4L, dto.getId()),
                  () -> assertEquals("boss",
                                     dto.getBoss()
                                        .getName()),
                  () -> assertEquals(3L,
                                     dto.getBoss()
                                        .getId()),
                  () -> assertNull(dto.getBoss()
                                      .getBoss()));
    }

}