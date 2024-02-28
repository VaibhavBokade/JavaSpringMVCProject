package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.example.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
/*
 * hear we declare various properties of task*/
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Task() {
        this.status = Status.NOT_COMPLETED;
    }
}
