package com.cmpt276_gp.gp;

import com.cmpt276_gp.gp.controllers.AdminController;
import com.cmpt276_gp.gp.models.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewExamRequests() {
        String expectedViewName = "users/admin/instructorRequests";
        
        String actualViewName = adminController.viewExamRequests();
        
        assertEquals(expectedViewName, actualViewName);
    }
    
}
