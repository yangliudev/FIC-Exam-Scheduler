package com.cmpt276_gp.gp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cmpt276_gp.gp.models.UserRepository;
import com.cmpt276_gp.gp.models.Users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

   @Test
    public void testValidLogin() throws Exception {
        Users adminUser = new Users("admin", "admin7", "admin", "admin@sfu.ca");
        when(userRepository.findByUsernameAndPassword("admin", "admin7")).thenReturn(adminUser);

        mockMvc.perform(post("/users/login")
                .param("username", "admin")
                .param("password", "admin7"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/admin"));

        verify(userRepository).findByUsernameAndPassword("admin", "admin7");
    }

    @Test
    public void testInvalidLogin() throws Exception {
        when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/users/login")
                .param("username", "invalid")
                .param("password", "invalid"))
                .andExpect(status().isOk())
                .andExpect(view().name("users/login"))
                .andExpect(model().attributeExists("error"));

        verify(userRepository).findByUsernameAndPassword("invalid", "invalid");
    }

    @Test
    public void testAddUser() throws Exception {
        mockMvc.perform(post("/users/add")
                .param("username", "unitTest")
                .param("password", "1")
                .param("userType", "teacher")
                .param("email", "newuser@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

        verify(userRepository).save(any(Users.class));
    }
    
}
