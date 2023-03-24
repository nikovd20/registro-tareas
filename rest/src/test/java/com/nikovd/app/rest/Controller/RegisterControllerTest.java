package com.nikovd.app.rest.Controller;

import com.nikovd.app.rest.Model.Task;
import com.nikovd.app.rest.Services.RegisterServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private RegisterServices    registerServices;

    private Task task;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        task = new Task();
        task.setTitle("Testing11");
        task.setDescription("Haciendo pruebas unitarias");
        task.setState("En proceso");

    }

    @DisplayName("Listar Tarea")
    @Test
    void getTasks(){
        when(registerController.getTasks()).thenReturn(Arrays.asList(task));
        assertNotNull(registerServices.getTasks());
    }

    @DisplayName("Guardar una tarea")
    @Test
    void saveTask(){
        when(registerController.saveTask(any())).thenReturn(task);
        assertNotNull(registerServices.saveTask(new Task()));
    }

    @DisplayName("Listar tarea por ID")
    @Test
    void findId(){
        when(registerController.findTaskbyId(task.getId())).thenReturn(Arrays.asList(task));
        assertNotNull(registerServices.findId(task.getId()));
    }

    @DisplayName("Eliminar tarea")
    @Test
    public void deleteTask() {
        String r = String.valueOf(registerController.deleteTaskbyId(Long.parseLong(String.valueOf(1))));
        assertNotNull(r);
    }




}