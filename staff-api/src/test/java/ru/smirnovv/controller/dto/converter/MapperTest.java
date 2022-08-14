package ru.smirnovv.controller.dto.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import ru.smirnovv.controller.dto.PageInfo;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты {@link Mapper}
 */
@DisplayName("Тест преобразователя сущностей")
class MapperTest {

    Mapper mapper = new Mapper(List.of(new PageInfoConverter()));

    @Test
    @DisplayName("Тест преобразования объекта с информации о пагинации")
    void shouldCorrectConvertEntity() {
        List<Pageable> result = mapper.convert(asList(createPageInfo(), createPageInfo()), Pageable.class);

        assertEquals(2, result.size());
    }

    private PageInfo createPageInfo() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setRecordCount(10);
        pageInfo.setSortField("id");
        return pageInfo;
    }

}