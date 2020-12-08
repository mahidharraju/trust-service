package com.org.trustservice;

import com.org.trustservice.model.Department;
import com.org.trustservice.model.DepartmentTrustGroup;
import com.org.trustservice.model.Permission;
import com.org.trustservice.model.TrustGroup;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import java.util.ArrayList;
import java.util.List;

public class InputDataCreator {

  public static List<DepartmentTrustGroup> getDepartmentTrustGroupData() {
    Permission readerPermission = Permission
        .builder()
        .name("Can view shared files and folders?")
        .permissionValue(true)
        .build();
    Permission commenterPermission = Permission
        .builder()
        .name("Can comment on shared files and folders?")
        .permissionValue(true)
        .build();
    Permission writerPermission = Permission
        .builder()
        .name("Can edit shared drives and folders?")
        .permissionValue(true)
        .build();
    Permission ownerPermission = Permission
        .builder()
        .name("Can transfer ownership?")
        .permissionValue(true)
        .build();
    Permission nonOwnerPermission = Permission
        .builder()
        .name("Can transfer ownership?")
        .permissionValue(false)
        .build();
    TrustGroup top100TG = TrustGroup
        .builder()
        .name("TOP 100")
        .build();
    TrustGroup top10000TG = TrustGroup
        .builder()
        .name("TOP 10000")
        .build();
    TrustGroupPermission tgReaderPermission = TrustGroupPermission
        .builder()
        .trustGroup(top100TG)
        .permission(readerPermission)
        .build();
    TrustGroupPermission tgWriterPermission = TrustGroupPermission
        .builder()
        .trustGroup(top100TG)
        .permission(writerPermission)
        .build();
    TrustGroupPermission tgCommenterPermission = TrustGroupPermission
        .builder()
        .permission(commenterPermission)
        .trustGroup(top100TG)
        .build();
    TrustGroupPermission tgOwnerPermission = TrustGroupPermission
        .builder()
        .trustGroup(top100TG)
        .permission(ownerPermission)
        .build();
    TrustGroupPermission tgNonWriterPermission = TrustGroupPermission
        .builder()
        .trustGroup(top10000TG)
        .permission(writerPermission)
        .build();
    TrustGroupPermission tgNonCommenterPermission = TrustGroupPermission
        .builder()
        .permission(commenterPermission)
        .trustGroup(top10000TG)
        .build();
    TrustGroupPermission tgNonReaderPermission = TrustGroupPermission
        .builder()
        .trustGroup(top10000TG)
        .permission(ownerPermission)
        .build();
    TrustGroupPermission tgNonOwnerPermission = TrustGroupPermission
        .builder()
        .trustGroup(top10000TG)
        .permission(nonOwnerPermission)
        .build();
    List<TrustGroupPermission> top100permissions = new ArrayList<>();
    top100permissions.add(tgCommenterPermission);
    top100permissions.add(tgOwnerPermission);
    top100permissions.add(tgReaderPermission);
    top100permissions.add(tgWriterPermission);
    List<TrustGroupPermission> top10000permissions = new ArrayList<>();
    top10000permissions.add(tgNonCommenterPermission);
    top10000permissions.add(tgNonOwnerPermission);
    top10000permissions.add(tgNonReaderPermission);
    top10000permissions.add(tgNonWriterPermission);
    TrustGroupFlavour top100flavour = TrustGroupFlavour
        .builder()
        .id(1L)
        .flavourDescription("Top 100 flavour")
        .role("owner")
        .trustGroupPermissions(top100permissions)
        .build();
    TrustGroupFlavour top10000flavour = TrustGroupFlavour
        .builder()
        .id(2L)
        .flavourDescription("Top 10000 flavour")
        .role("writer")
        .trustGroupPermissions(top10000permissions)
        .build();
    DepartmentTrustGroup departmentTrustGroup = DepartmentTrustGroup
        .builder()
        .flavour(top100flavour)
        .dept(Department.builder().id(1L).name("SALES").build())
        .build();
    DepartmentTrustGroup departmentTrustGroup1 = DepartmentTrustGroup
        .builder()
        .flavour(top10000flavour)
        .dept(Department.builder().id(1L).name("SALES").build())
        .build();
    List<DepartmentTrustGroup> departmentTrustGroupList = new ArrayList<>();
    departmentTrustGroupList.add(departmentTrustGroup);
    departmentTrustGroupList.add(departmentTrustGroup1);
    return departmentTrustGroupList;
  }
}
