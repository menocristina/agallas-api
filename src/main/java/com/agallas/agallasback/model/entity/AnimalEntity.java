package com.agallas.agallasback.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ANIMAL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CHIP")
    private String chip;

    @Column(name = "RACE")
    private String race;

    @Column(name = "AGALLAS_DATE")
    private Date agallasDate;

    @Column(name = "ORIGIN")
    private String origin;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "LAST_INTERNAL_DEWORMING")
    private Date lastInternalDeworming;

    @Column(name = "LAST_EXTERNAL_DEWORMING")
    private Date lastExternalDeworming;

    @Column(name = "FIRST_VACINE")
    private Boolean firstVacine;

    @Column(name = "SECOND_VACINE")
    private Boolean secondVacine;

    @Column(name = "GENERAL_ANALYTICS")
    private Boolean generalAnalytics;

    @Column(name = "MEDITERRANEANS")
    private Boolean mediterraneans;

    @Column(name = "CASTRATION")
    private Boolean castration;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.DATE)
    private Date deletedAt;

    @Column(name = "PHOTO_GAL1")
    private byte[] photoGal1;

    @Column(name = "PHOTO_GAL2")
    private byte[] photoGal2;

    @Column(name = "PHOTO_GAL3")
    private byte[] photoGal3;

    @Column(name = "PHOTO_GAL4")
    private byte[] photoGal4;

    @OneToMany(mappedBy = "animal")
    private List<AnimalStateEntity> states;

}
