package com.example.verifit.controller;

import com.example.verifit.controller.MemberController;
import com.example.verifit.model.Member;
import com.example.verifit.service.MemberServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTests {
    @Mock
    private MemberServiceImpl memberService;
    private Member member;
    private List<Member> members;
    @InjectMocks
    private MemberController memberController;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        member = new Member(LocalDateTime.parse("2023-05-17T11:50:32.777"), 2, false);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @AfterEach
    void tearDown() {
        member = null;
    }

    @Test
    public void PatchMappingOfMembersAttendance() throws Exception {
        doNothing()
                .when(memberService)
                .updateMembersAttendance(member.getMembershipId(), member.getLastAttendanceDate());

        mockMvc.perform(patch("/api/v1/members/0/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(member)))
                .andExpect(status().isNoContent());

        verify(memberService,times(1)).updateMembersAttendance(any(), any());
    }
    @Test
    public void GetMappingOfMembersDiscountById() throws Exception {
        when(memberService.getMembersDiscountStatusById(member.getMembershipId()))
                .thenReturn("Members discount status: " + member.getDiscountStatus());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members/0/discount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfMembersCurrentStreakById() throws Exception {
        when(memberService.getMembersStreakStatusById(member.getMembershipId()))
                .thenReturn("Members current streak: " + member.getCurrentStreak());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members/0/streak")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void GetMappingOfAllMembers() throws Exception {
        when(memberService.getAllMembers()).thenReturn(members);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(member)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        verify(memberService).getAllMembers();
        verify(memberService,times(1)).getAllMembers();
    }

    public static String asJsonString(final Object obj){
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
