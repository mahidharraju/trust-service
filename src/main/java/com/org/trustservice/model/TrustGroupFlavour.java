package com.org.trustservice.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trustgroup_flavour")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustGroupFlavour {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID tgFlavourId;
    private String role;
    private String flavourDescription;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime expiryDate;
    private String createdBy;
    private String updatedBy;

    @JsonIgnore
    @OneToMany(mappedBy = "flavour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TrustGroupPermission> trustGroupPermissions;

    @JsonIgnore
    @OneToMany(mappedBy = "flavour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DepartmentTrustGroup> departmentTrustGroups;
}
