package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*In this controller we write various view controller to just call the jsp pages from view file*/
@Controller
public class ViewController {

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginUser() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login/introTaskManager")
    public String introTaskManager() {
        return "introTaskManager";
    }

    @GetMapping("/introTaskManager/userDashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/addTask")
    public String addTask() {
        return "addTask";
    }

    @GetMapping("/displayTasks")
    public String getTasksForUser() {
        return "displayTotalTasks";
    }

    @GetMapping("/sortByPriority")
    public String sortTasksByPriority() {
        return "sortTasksByPriority";
    }

    @GetMapping("/sortByDueDate")
    public String sortTasksByDueDate() {
        return "sortTasksByDueDate";
    }

    @GetMapping("/edit")
    public String editTask() {
        return "editTask";
    }

    @GetMapping("/searchTask")
    public String searchTask() {
        return "searchByTitle";
    }

    @GetMapping("/searchByDate")
    public String searchTaskByDate() {
        return "searchByDueDate";
    }

    @GetMapping("/displayCompletedTasks")
    public String getCompletedTasksForUser() {
        return "displayTotalCompletedTasks";
    }

}
