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
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "dept_id_seq", initialValue = 1, allocationSize = 100)
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_id_seq")
  private Long id;
  private String name;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private String createdBy;
  private String updatedBy;
  @JsonIgnore
  @OneToMany(
      mappedBy = "dept",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.SELECT)
  private Set<DepartmentTrustGroup> departmentTrustGroups;
}
