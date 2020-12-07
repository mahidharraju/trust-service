package com.org.trustservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.org.trustservice.dto.TrustGroupUpdateDTO;
import com.org.trustservice.dto.TrustGroupUpdateResponseDTO;
import com.org.trustservice.repository.TrustGroupRepository;
import com.org.trustservice.service.TrustGroupService;
import com.org.trustservice.util.Constants;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.WebRequest;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrustGroupPermissionController.class)
public class TrustGroupPermissionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TrustGroupService trustGroupService;

  @MockBean
  private TrustGroupRepository trustGroupRepository;

  @MockBean
  WebRequest webRequest;

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
    doNothing().when(trustGroupService).updateTrustGroups(updateRequest);
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
}
