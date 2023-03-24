package com.nikovd.app.rest.Model;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Table(schema = "TasksCrud")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    @Column( nullable = false)
    @NotBlank(message = "El title no puede estar en blanco")
    private String title;
    @Column ( nullable = false)
    @NotBlank (message = "La descripcion no puede estar en blanco")
    private String description;
    @Column ( nullable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column ( nullable = false)
    @NotBlank (message = "El estado no puede estar en blanco")
    private String state;

    public long getId(){
        return id;
    }
    public void setId(long s){
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
    public void setState(String state) {
        this.state = state;
    }

}
