package ru.smirnovv.controller.dto.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.entity.Organization;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.smirnovv.utils.Utils.setId;

/**
 * Тесты {@link OrganizationDTOConverter}
 */
@DisplayName("Тест преобразователя сущности организации")
class OrganizationDTOConverterTest {

    OrganizationDTOConverter converter = new OrganizationDTOConverter();
    Mapper mapper = new Mapper(List.of(converter));

    @Test
    @DisplayName("Тест преобразования объекта с информации об организации")
    void shouldCorrectConvertEntity() {
        Organization head = setId(new Organization(), 2L);
        head.setName("head");
        head.setHeadOrganization(new Organization());

        Organization source = setId(new Organization(), 1L);
        source.setName("source");
        source.setHeadOrganization(head);

        OrganizationDTO dto = mapper.convert(source, OrganizationDTO.class);

        assertAll(() -> assertEquals(1L, dto.getId()),
                  () -> assertEquals("source", dto.getName()),
                  () -> assertEquals(2L,
                                     dto.getHeadOrganization()
                                        .getId()),
                  () -> assertEquals("head",
                                     dto.getHeadOrganization()
                                        .getName()),
                  () -> assertNull(dto.getHeadOrganization()
                                      .getHeadOrganization()));
    }

}