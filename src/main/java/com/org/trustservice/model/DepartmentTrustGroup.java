package com.org.trustservice.model;

import com.org.trustservice.model.id.DepartmentTrustGroupID;
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


@Entity
@Table(name = "department_trustgroups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(DepartmentTrustGroupID.class)
@SequenceGenerator(name = "dept_tg_id_seq", initialValue = 1, allocationSize = 100)
public class DepartmentTrustGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_tg_id_seq")
  private Long id;

  @Id
  @Column(insertable = false, updatable = false)
  private Long deptId;

  @Id
  @Column(insertable = false, updatable = false)
  private Long tgFlavourId;

  @Id
  @Column(insertable = false, updatable = false)
  private Long orgCollabId;

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
