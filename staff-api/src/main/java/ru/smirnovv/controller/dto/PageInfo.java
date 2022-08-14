package ru.smirnovv.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageInfo {

    private int recordCount;
    private int pageNumber;
    private String sortField;

}
