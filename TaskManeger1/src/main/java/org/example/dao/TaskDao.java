package org.example.dao;

import org.example.entities.Task;

import java.util.List;

/*In this interface we declare the various methods related to task and implement it in TaskDaoImpl*/
public interface TaskDao {

    Task addTaskForUser(int userId, Task task);

    List<Task> getTasksForUser(int userId);

    List<Task> searchTasksByTitle(List<Task> tasks, String title);

    List<Task> searchTasksByDueDate(List<Task> tasks, String dueDate);

    void removeTasks(List<Task> tasksToBeRemoved);

    void markCompleted(List<Task> tasksToBeMarkedAsCompleted);

    void saveUpdatedTask(Task updatedTask);

    Task findById(int taskId);

    void deleteTask(int taskId);

}
