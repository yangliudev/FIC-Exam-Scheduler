package com.cmpt276_gp.gp;

import com.cmpt276_gp.gp.models.Instructor;
import com.cmpt276_gp.gp.controllers.InstructorController;
import com.cmpt276_gp.gp.models.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class InstructorControllerTest {
    private InstructorController instructorController;

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instructorController = new InstructorController();
        instructorController.instRepo = instructorRepository;
    }

    @Test
    void testCreateRequest() {
        Map<String, String> instructorData = new HashMap<>();
        instructorData.put("course_name", "Test Course");
        instructorData.put("duration", "60");
        instructorData.put("section", "1");
        instructorData.put("firstChoice", "2023-07-17T10:00:00");
        instructorData.put("secondChoice", "2023-07-18T14:00:00");
        instructorData.put("thirdChoice", "2023-07-19T16:00:00");
        instructorData.put("instructorUser", "testuser");

        Instructor savedInstructor = new Instructor(
                "Test Course",
                60,
                1,
                LocalDateTime.parse("2023-07-17T10:00:00"),
                LocalDateTime.parse("2023-07-18T14:00:00"),
                LocalDateTime.parse("2023-07-19T16:00:00"),
                "testuser"
        );

        when(instructorRepository.save(any(Instructor.class))).thenReturn(savedInstructor);

        String result = instructorController.createRequest(instructorData);

        assertEquals("redirect:/dashboard/teacher", result);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }
    
}
