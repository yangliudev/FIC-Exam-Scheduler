package com.cmpt276_gp.gp;

import com.cmpt276_gp.gp.models.Admin;
import com.cmpt276_gp.gp.models.AdminRepository;
import com.cmpt276_gp.gp.controllers.AdminController;
import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.models.InstructorRepository;
import com.cmpt276_gp.gp.models.UserRepository;
import com.cmpt276_gp.gp.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {
    @Mock
    private AdminRepository adminRepo;

    @Mock
    private InstructorRepository instRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewExamRequests() {
        List<Instructor> teacherTable = new ArrayList<>();
        teacherTable.add(new Instructor());
        when(instRepo.findAll()).thenReturn(teacherTable);

        Model model = mock(Model.class);
        String viewName = adminController.viewExamRequests(model);

        assertEquals("users/admin/instructorRequests", viewName);
        verify(instRepo, times(1)).findAll();
        verify(model, times(1)).addAttribute("teacherTable", teacherTable);
    }


}
