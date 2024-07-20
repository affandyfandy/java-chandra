package org.assignment1.assignment1.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class TitleDTO {

    private Integer empNo; // Employee number from TitleId
    private String title; // Title from TitleId
    private Date fromDate; // From date from TitleId
    private Date toDate; // To date from Title entity
}