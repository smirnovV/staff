package ru.smirnovv.controller.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.smirnovv.controller.dto.OrganizationDTO;
import ru.smirnovv.entity.Organization;

@Service
public class OrganizationDTOConverter implements Converter {

    @Override
    public void buildConverter(ModelMapper modelMapper) {
        modelMapper.addConverter(mappingContext -> {
            OrganizationDTO result = new OrganizationDTO();
            Organization source = mappingContext.getSource();
            result.setId(source.getId());
            result.setName(source.getName());

            Organization head = source.getHeadOrganization();
            if (head != null) {
                result.setHeadOrganization(new OrganizationDTO());
                result.getHeadOrganization().setId(head.getId());
                result.getHeadOrganization().setName(head.getName());
            }

            return result;
        }, Organization.class, OrganizationDTO.class);
    }

}
