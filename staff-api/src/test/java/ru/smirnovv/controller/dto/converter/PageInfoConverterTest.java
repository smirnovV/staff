package ru.smirnovv.controller.dto.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import ru.smirnovv.controller.dto.PageInfo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты {@link PageInfoConverter}
 */
@DisplayName("Тест преобразователя информации о пагинации")
class PageInfoConverterTest {

    Mapper mapper = new Mapper(List.of(new PageInfoConverter()));

    @Test
    @DisplayName("Тест преобразования объекта с информации о пагинации")
    void shouldCorrectConvertEntity() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setRecordCount(10);
        pageInfo.setSortField("id");

        Pageable pageable = mapper.convert(pageInfo, Pageable.class);

        assertAll(() -> assertEquals(pageable.getPageNumber(), 1),
                  () -> assertEquals(pageable.getPageSize(), 10),
                  () -> assertEquals(pageable.getSort()
                                             .stream()
                                             .count(), 1L));
    }

}