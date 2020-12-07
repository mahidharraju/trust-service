package com.org.trustservice.model;

import com.org.trustservice.model.id.TrustGroupPermissionId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "trustgroup_permissions_def")
@Entity
@IdClass(TrustGroupPermissionId.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "trustgroup_permission_id_seq", initialValue = 1, allocationSize = 100)
@Builder
public class TrustGroupPermission {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trustgroup_permission_id_seq")
  private Long id;

  @Column(insertable = false, updatable = false)
  private Long tgFlavourId;

  @Column(insertable = false, updatable = false)
  private Long trustGroupId;

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
