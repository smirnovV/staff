package ru.smirnovv.controller.dto.converter;

import org.modelmapper.ModelMapper;

public interface Converter {

    void buildConverter(ModelMapper modelMapper);

}
