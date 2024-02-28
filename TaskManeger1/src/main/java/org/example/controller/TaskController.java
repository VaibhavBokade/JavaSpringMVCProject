package org.example.controller;

import org.example.entities.Task;
import org.example.enums.Status;
import org.example.exceptions.CompletedTasksNotFoundException;
import org.example.exceptions.TaskNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.handler.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/*
 * In this controller we write task related methods or api*/
@Controller
public class TaskController {

    @Autowired
    private TaskHandler taskHandler;

    /*In this controller we can add the task for particular user using userId*/
    @PostMapping(value = "/addTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> addTaskForUser(
            @RequestHeader(value = "userId") int userId,
            @RequestBody Task task) {
        try {
            return ResponseEntity.ok(taskHandler.addTaskForUser(userId, task));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this controller we display all the tasks for particular user using their userId*/
    @GetMapping("/displayTotalTasks")
    public ResponseEntity<List<Task>> getTasksForUser(
            @RequestHeader(value = "userId") int userId) {
        try {
            List<Task> tasks = taskHandler.getTasksForUser(userId);
            return ResponseEntity.ok(tasks);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    /*In this controller we sort the tasks using priority*/
    @GetMapping("/sortTasksByPriority")
    public ResponseEntity<List<Task>> getSortedTaskForUserByPriority(
            @RequestHeader(value = "userId") int userId) {
        try {
            List<Task> tasks = taskHandler.getSortedTaskForUserByPriority(userId);
            return ResponseEntity.ok(tasks);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this controller we sort the tasks using Due date*/
    @GetMapping("/sortTasksByDueDate")
    public ResponseEntity<List<Task>> getSortedTaskForUserByDueDate(
            @RequestHeader(value = "userId") int userId) {
        try {
            return ResponseEntity.ok(taskHandler.getSortedTaskForUserByDueDate(userId));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this controller we search task using the task title */
    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Task>> searchTasksByTitle(
            @RequestHeader(value = "userId") int userId,
            @RequestParam String title) {
        try {
            List<Task> tasks = taskHandler.searchTasksByTitle(userId, title);
            if (!tasks.isEmpty()) {
                return ResponseEntity.ok(tasks);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this controller we search task using the task dae date */
    @GetMapping("/searchByDueDate")
    public ResponseEntity<List<Task>> searchTasksByDueDate(
            @RequestHeader(value = "userId") int userId,
            @RequestParam String dueDate) {
        try {
            List<Task> tasks = taskHandler.searchTasksByDueDate(userId, dueDate);
            if (!tasks.isEmpty()) {
                return ResponseEntity.ok(tasks);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /*In this we can mark task as completed*/
    @PostMapping("/markTaskAsCompleted")
    public ResponseEntity<String> markTaskAsCompleted(
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "taskId") int taskId) {
        try {
            String response = taskHandler.markTaskAsCompleted(userId, taskId);
            return ResponseEntity.ok(response);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    /*In this controller we can remove the task whose status is Completed*/
    @GetMapping("/removeCompletedTasks")
    public ResponseEntity<String> removeCompletedTasks(
            @RequestHeader(value = "userId") int userId) {
        try {
            String response = taskHandler.removeCompletedTasks(userId);
            return ResponseEntity.ok(response);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found in List !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    /*In this controller we can display all the completed tasks*/
    @GetMapping("/displayTotalCompletedTasks")
    public ResponseEntity<List<Task>> getCompletedTasks(@RequestHeader(value = "userId") int userId) {
        try {
            List<Task> tasks = taskHandler.getCompletedTasks(userId);
            return ResponseEntity.ok(tasks);
        } catch (CompletedTasksNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    /*In this controller we can edit the task details*/
    @PutMapping("/editTask")
    public ResponseEntity<String> editTask(
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "title") String title,
            @RequestBody Task updatedTask) {
        try {
            String response = taskHandler.editTask(userId, title, updatedTask);
            return ResponseEntity.ok(response);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    /*In this Controller we can see the status of particular task*/
    @GetMapping("/showStatus")
    public ResponseEntity<?> showStatus(@RequestHeader(value = "userId") int userId, @RequestHeader(value = "title") String title) {
        try {
            Status status = taskHandler.showStatus(userId, title);
            return ResponseEntity.ok(status);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/getTaskDetails")
    public ResponseEntity<Task> getTaskDetails(
            @RequestHeader(value = "userId") int userId,
            @RequestHeader(value = "taskId") int taskId) {
        try {
            Task task = taskHandler.getTaskDetails(userId, taskId);
            return ResponseEntity.ok(task);
        } catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/deleteTask")
    public ResponseEntity<String> deleteTaskFromList(@RequestHeader(value = "taskId") int taskId) {
        try{
            String response = taskHandler.deleteTask(taskId);
            return ResponseEntity.ok(response);
        }catch (TaskNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found in List !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
