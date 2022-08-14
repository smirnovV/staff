package ru.smirnovv.controller.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.smirnovv.controller.dto.PageInfo;

@Service
public class PageInfoConverter implements Converter {

    @Override
    public void buildConverter(ModelMapper modelMapper) {
        modelMapper.addConverter(mappingContext -> PageRequest.of(
                mappingContext.getSource().getPageNumber(), mappingContext.getSource().getRecordCount(),
                Sort.DEFAULT_DIRECTION, mappingContext.getSource().getSortField()), PageInfo.class, Pageable.class);
    }

}
