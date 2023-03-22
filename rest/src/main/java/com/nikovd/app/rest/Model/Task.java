package com.nikovd.app.rest.Model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(schema = "TasksCrud")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column
    @NotNull
    @NotEmpty(message = "El title no puede estar en blanco")
    private String title;
    @Column
    private String description;
    @Column
    @CreationTimestamp
    private Date creationDate;
    @Column
    private String state;

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreationDate(){
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public String getState(){
        return state;
    }
    public void setstate(String state) {
        this.state = state;
    }

}
