package com.nikovd.app.rest.Services;


import com.nikovd.app.rest.Model.Task;
import com.nikovd.app.rest.Repository.RegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegisterServicesTest {

    @Mock
    private RegisterRepository registerRepository;
    @InjectMocks
    private RegisterServices registerServices;

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
        when(registerRepository.findAll()).thenReturn(Arrays.asList(task));
        assertNotNull(registerServices.getTasks());
    };

    @DisplayName("Guardar una tarea")
    @Test
    void saveTask(){
        when(registerRepository.save(any(Task.class))).thenReturn(task);
        assertNotNull(registerServices.saveTask(new Task()));
    }

    @DisplayName("Listar tarea por ID")
    @Test
    void findId(){
        when(registerRepository.findById(task.getId())).thenReturn(Arrays.asList(task));
        assertNotNull(registerServices.findId(task.getId()));
    }

    @DisplayName("Eliminar tarea")
    @Test
    void deleteTask(){
        registerRepository.save(task);
        registerRepository.deleteById(task.getId());
        List<Task> taskList = registerRepository.findById(task.getId());
        if (!(taskList).isEmpty()) throw new AssertionError();
    }
}