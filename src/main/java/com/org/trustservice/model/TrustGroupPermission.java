package com.org.trustservice.model;

import com.org.trustservice.model.id.TrustGroupPermissionId;
import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
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
