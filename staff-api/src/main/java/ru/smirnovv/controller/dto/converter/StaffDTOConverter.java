package ru.smirnovv.controller.dto.converter;

import org.modelmapper.ModelMapper;
import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.controller.dto.StaffDTO;
import ru.smirnovv.entity.Staff;

public class StaffDTOConverter implements Converter {

    @Override
    public void buildConverter(ModelMapper modelMapper) {
        modelMapper.addConverter(mappingContext -> {
            StaffDTO result = new StaffDTO();
            Staff source = mappingContext.getSource();
            result.setId(source.getId());
            result.setName(source.getName());
            result.setOrganization(modelMapper.map(source.getOrganization(), OrganizationDTO.class));

            Staff boss = source.getBoss();
            if (boss != null) {
                result.setBoss(new StaffDTO());
                result.getBoss().setId(boss.getId());
                result.getBoss().setName(boss.getName());
                result.getBoss().setOrganization(result.getOrganization());
            }


            return result;
        }, Staff.class, StaffDTO.class);
    }

}
