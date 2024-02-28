package org.example.handler;

import org.example.entities.Task;
import org.example.enums.Status;
import org.example.exceptions.TaskNotFoundException;

import java.util.List;

/*In this interface, we mention some methods related to tasks and implement them in the TaskHandlerImpl class*/
public interface TaskHandler {

    Task addTaskForUser(int userId, Task task);

    List<Task> getTasksForUser(int userId);

    List<Task> getSortedTaskForUserByPriority(int userId);

    List<Task> getSortedTaskForUserByDueDate(int userId);

    List<Task> searchTasksByTitle(int userId, String title);

    List<Task> searchTasksByDueDate(int userId, String dueDate);

    String markTaskAsCompleted(int userId, int taskId);

    String removeCompletedTasks(int userId);

    List<Task> getCompletedTasks(int userId);

    String editTask(int userId, String title, Task updatedTask);

    Status showStatus(int userId, String title);

    Task getTaskDetails(int userId, int taskId);

    String deleteTask(int taskId);
}
