package com.nikovd.app.rest.Services;


import com.nikovd.app.rest.Model.Task;
import com.nikovd.app.rest.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RegisterServices {

    @Autowired
    private RegisterRepository registerRepository;

    public List<Task> getTasks(){
        return (List<Task>) registerRepository.findAll();
    }
    public Task saveTask(Task task){
        return registerRepository.save(task);
    }
    public List<Task> findId(long id){
        return registerRepository.findById(id);
    }
    public boolean deleteTask(long id) {
        try{
            registerRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false ;
        }
    }

}
