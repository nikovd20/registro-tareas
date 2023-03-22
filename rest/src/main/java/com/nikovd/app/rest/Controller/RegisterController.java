package com.nikovd.app.rest.Controller;

import com.nikovd.app.rest.Model.Task;
import com.nikovd.app.rest.Services.RegisterServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    RegisterServices registerServices;

    @GetMapping()
    public ArrayList<Task> getTasks(){
        return registerServices.getTasks();
    }
    @PostMapping()
    public Task saveTask(@Validated @RequestBody Task task){
        return this.registerServices.saveTask(task);
    }
    @GetMapping( path = "{id}")
    public ArrayList<Task> findTaskbyId(@PathVariable("id") long id){
        return this.registerServices.findId(id);
    }
    @DeleteMapping(value="{id}")
    public String deleteTaskbyId(@PathVariable("id") long id){
        boolean ok = this.registerServices.deleteTask(id);
        if (ok){
            return "Se elimino la tarea con id" + id;
        }else {
            return "No se pudo eliminar la tarea con id" + id;
        }
    }

}
