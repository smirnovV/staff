package ru.smirnovv.controller.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mapper {

    private final ModelMapper modelMapper;

    public Mapper(List<Converter> converters) {
        this.modelMapper = new ModelMapper();

        converters.forEach(converter -> {
            converter.buildConverter(modelMapper);
        });
    }

    public <S, D> List<D> convert(List<S> list, Class<D> destination) {
        return list.stream().map(o -> this.convert(o, destination)).collect(Collectors.toList());
    }

    public <S, D> D convert(S source, Class<D> destination) {
        return modelMapper.map(source, destination);
    }

}
