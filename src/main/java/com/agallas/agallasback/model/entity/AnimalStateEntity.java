package com.agallas.agallasback.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ANIMAL_STATE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalStateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "STATE_TYPE")
    @ManyToOne(fetch = FetchType.LAZY)
    private StateTypeEntity stateType;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @JoinColumn(name = "ANIMAL")
    @ManyToOne(fetch = FetchType.LAZY)
    private AnimalEntity animal;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.DATE)
    private Date deletedAt;
}
