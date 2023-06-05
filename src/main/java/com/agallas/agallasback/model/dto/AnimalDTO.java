package com.agallas.agallasback.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AnimalDTO {
    private Long id;
    private String name;
    private String chip;
    private String race;
    private Date agallasDate;
    private String origin;
    private String description;
    private byte[] photo;
    private Date lastInternalDeworming;
    private Date lastExternalDeworming;
    private Boolean firstVacine;
    private Boolean secondVacine;
    private Boolean generalAnalytics;
    private Boolean mediterraneans;
    private Boolean castration;
    private byte[] photoGal1;
    private byte[] photoGal2;
    private byte[] photoGal3;
    private byte[] photoGal4;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Integer actualStateType;
    private Date actualStateDate;
}
