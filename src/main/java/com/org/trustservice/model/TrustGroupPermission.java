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

import com.org.trustservice.model.id.TrustGroupPermissionId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "trustgroup_permissions_def")
@Entity
@IdClass(TrustGroupPermissionId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrustGroupPermission {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(insertable = false, updatable = false)
    private UUID tgFlavourId;

    @Column(insertable = false, updatable = false)
    private UUID trustGroupId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tgFlavourId")
    private TrustGroupFlavour flavour;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trustGroupId")
    private TrustGroup trustGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permissionId")
    private Permission permission;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime expiryDate;
    private String createdBy;
    private String updatedBy;
}
