package com.org.trustservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.org.trustservice.model.id.DepartmentTrustGroupID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department_trustgroups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(DepartmentTrustGroupID.class)
public class DepartmentTrustGroup {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Id
    @Column(insertable = false, updatable = false)
    private UUID deptId;

    @Id
    @Column(insertable = false, updatable = false)
    private UUID tgFlavourId;

    @Id
    @Column(insertable = false, updatable = false)
    private UUID orgCollabId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deptId")
    private Department dept;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tgFlavourId")
    private TrustGroupFlavour flavour;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime expiryDate;
    private String createdBy;
    private String updatedBy;
}
