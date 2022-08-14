package ru.smirnovv.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StaffDTO {

    private Long id;
    private String name;
    private OrganizationDTO organization;
    private StaffDTO boss;

}
