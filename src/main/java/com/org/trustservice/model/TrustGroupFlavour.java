package com.org.trustservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "trustgroup_flavour")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "trustgroup_flavour_id_seq", initialValue = 1, allocationSize = 100)
public class TrustGroupFlavour {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trustgroup_flavour_id_seq")
  private Long id;
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
