package com.org.trustservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.model.Permission;
import com.org.trustservice.model.TrustGroupFlavour;
import com.org.trustservice.model.TrustGroupPermission;
import com.org.trustservice.service.TrustGroupService;
import com.org.trustservice.util.Constants;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrustGroupPermissionController.class)
class TrustGroupPermissionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TrustGroupService trustGroupService;


  @Test
  void testGetTrustGroupsByDepartment() throws Exception {
    String url = "/departments/{departmentId}/trustGroups";
    TrustGroupUpdateResponseDTO responseDto = new TrustGroupUpdateResponseDTO();
    Mockito.when(trustGroupService.getAllTrustGroupsByDepartment(Mockito.anyLong(), Mockito.anyLong())).thenReturn(responseDto);
    mockMvc.perform(get(url, 123L).header(Constants.ORG_COLLAB_HEADER, 123L)).andExpect(status().isOk());
  }

  @Test
  void testMissingHeaderForgetTrustGroupsByDepartment() throws Exception {
    String url = "/departments/{departmentId}/trustGroups";
    TrustGroupUpdateResponseDTO responseDto = new TrustGroupUpdateResponseDTO();
    Mockito.when(trustGroupService.getAllTrustGroupsByDepartment(Mockito.anyLong(), Mockito.anyLong())).thenReturn(responseDto);
    mockMvc.perform(get(url, 123L)).andExpect(status().isInternalServerError());
  }


  @Test
  void testUpdateTrustGroup() throws Exception {
    Permission editorPermission = Permission
        .builder()
        .name("Can edit shared drives and folders?")
        .permissionValue(true)
        .build();
    Permission viewerPermission = Permission
        .builder()
        .name("Can view shared files and folders?")
        .permissionValue(true)
        .build();
    Permission ownerPermission = Permission
        .builder()
        .name("Can transfer ownership??")
        .permissionValue(true)
        .build();
    Permission commenterPermission = Permission
        .builder()
        .name("Can comment on shared files and folders?")
        .permissionValue(true)
        .build();
    List<TrustGroupPermission> tgPermissions = new ArrayList<>();
    tgPermissions.add(TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .permission(editorPermission)
        .build());
    tgPermissions.add(TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .permission(viewerPermission)
        .build());
    tgPermissions.add(TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .permission(commenterPermission)
        .build());
    tgPermissions.add(TrustGroupPermission
        .builder()
        .tgFlavourId(1L)
        .permission(ownerPermission)
        .build());
    TrustGroupFlavour response = TrustGroupFlavour
        .builder()
        .trustGroupPermissions(tgPermissions)
        .build();
    TrustGroupUpdateDTO updateRequest = TrustGroupUpdateDTO
        .builder()
        .name("Top 100")
        .canTransferOwnership(true)
        .canViewSharedFilesAndFolders(true)
        .canCommentOnSharedFilesAndFolders(true)
        .canEditSharedDrivesAndFolders(true)
        .build();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(updateRequest);
    String url = "/departments/{departmentId}/trustGroups";
    Mockito.when(trustGroupService.updateTrustGroups(updateRequest)).thenReturn(response);
    mockMvc.perform(put(url, 123L).header(Constants.ORG_COLLAB_HEADER, 123L).contentType(APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isOk());
  }

  @Test
  void testGetTrustGroupRole() throws Exception {
    Long trustGroupId = 123L;
    Mockito.when(trustGroupService.getRoleBasedOnFlavour(trustGroupId)).thenReturn("writer");
    String url = "/trustGroups/{trustGroupId}/role";
    mockMvc.perform(get(url, trustGroupId)).andExpect(status().isOk());
  }

  @Test
  void testMissingHeaderInfoUpdateTrustGroup() throws Exception {
    TrustGroupUpdateDTO updateRequest = TrustGroupUpdateDTO
        .builder()
        .name("Top 100")
        .canTransferOwnership(true)
        .canViewSharedFilesAndFolders(true)
        .canCommentOnSharedFilesAndFolders(true)
        .canEditSharedDrivesAndFolders(true)
        .build();
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson = ow.writeValueAsString(updateRequest);
    String url = "/departments/{departmentId}/trustGroups";
    mockMvc.perform(put(url, 123L).contentType(APPLICATION_JSON)
        .content(requestJson)).andExpect(status().isInternalServerError());
  }
}
