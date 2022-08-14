package ru.smirnovv.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationDTO {

    private Long id;
    private String name;
    private OrganizationDTO headOrganization;

}
