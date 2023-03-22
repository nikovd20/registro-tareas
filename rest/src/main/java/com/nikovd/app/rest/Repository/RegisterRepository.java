package com.nikovd.app.rest.Repository;

import com.nikovd.app.rest.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RegisterRepository extends JpaRepository<Task, Long> {
    public abstract ArrayList<Task> findById(long id);

}
