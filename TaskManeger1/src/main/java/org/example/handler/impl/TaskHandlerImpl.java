package org.example.handler.impl;

import org.apache.log4j.Logger;
import org.example.comparators.SortByDueDate;
import org.example.comparators.SortByPriority;
import org.example.dao.TaskDao;
import org.example.dao.UserDao;
import org.example.entities.Task;
import org.example.entities.User;
import org.example.enums.Status;
import org.example.exceptions.CompletedTasksNotFoundException;
import org.example.exceptions.TaskNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.example.handler.TaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*In this class we implement the TaskHandler*/
@Component
public class TaskHandlerImpl implements TaskHandler {

    private static final Logger logger = Logger.getLogger(TaskHandlerImpl.class);

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserDao userDao;

    /*In this class we can add the tasks for a particular user
     * First get userId and task and save that task for particular user*/
    @Override
    public Task addTaskForUser(int userId, Task task) throws UserNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: addTaskForUser");
            return taskDao.addTaskForUser(userId, task);
        } catch (UserNotFoundException e) {
            logger.error("User not found Exception");
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during adding task: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: addTaskForUser");
        }
    }

    /*In this method we get the tasks for user
     * Get all the tasks for all the user*/
    @Override
    public List<Task> getTasksForUser(int userId) throws UserNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: getTasksForUser");
            return taskDao.getTasksForUser(userId);
        } catch (UserNotFoundException e) {
            logger.error("User not found Exception");
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during fetching tasks: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getTasksForUser");
        }
    }

    /*In this we get the sorted list using priority
     * First we get the tasks by using userId
     * Then call the SortByPriority class
     * And then return tasks*/
    @Override
    public List<Task> getSortedTaskForUserByPriority(int userId) throws UserNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: getSortedTaskForUserByPriority");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            Collections.sort(tasks, new SortByPriority());
            return tasks;
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during sorting tasks by priority: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getSortedTaskForUserByPriority");
        }
    }

    /*In this we get the sorted list using DueDate
     * First we get the tasks by using userId
     * Then call the SortByDueDate class
     * And then return tasks*/
    @Override
    public List<Task> getSortedTaskForUserByDueDate(int userId) throws UserNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: getSortedTaskForUserByDueDate");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            Collections.sort(tasks, new SortByDueDate());
            return tasks;
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during sorting tasks by due date: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getSortedTaskForUserByDueDate");
        }
    }

    /*Hear we can search the task using title
     * First we send the title to searchTasksByTitle and get task
     * Then add that in list and send return*/
    @Override
    public List<Task> searchTasksByTitle(int userId, String title) throws TaskNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: searchTasksByTitle");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            return taskDao.searchTasksByTitle(tasks, title);
        } catch (TaskNotFoundException e) {
            logger.warn("Task not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during searching tasks by title: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: searchTasksByTitle");
        }
    }


    /*Hear we can search the task using DueDate
     * First we send the DueDate to searchTasksByDueDate and get task
     * Then add that in list and send return*/
    @Override
    public List<Task> searchTasksByDueDate(int userId, String dueDate) throws TaskNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: searchTaskByTitle");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            return taskDao.searchTasksByDueDate(tasks, dueDate);
        } catch (TaskNotFoundException e) {
            logger.warn("Task not found: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during searching tasks by title: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: searchTaskByTitle");
        }
    }

    /*In this we mark task as done
     * First we get all the tasks for particular user using userId
     * Then get the task using title
     * Then update the status to completed
     * Then add the task in tasksToBeMarkedAsCompleted list*/
    @Override
    public String markTaskAsCompleted(int userId, int taskId) throws TaskNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: markTaskAsCompleted");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            List<Task> tasksToBeMarkedAsCompleted = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getId()==taskId) {
                    task.setStatus(Status.COMPLETED);
                    tasksToBeMarkedAsCompleted.add(task);
                }
            }
            taskDao.markCompleted(tasksToBeMarkedAsCompleted);
            if (tasksToBeMarkedAsCompleted.isEmpty()) {
                throw new TaskNotFoundException("Task not found !");
            }
            return "Task marked as completed successfully !";
        } catch (Exception e) {
            logger.error("An error occurred during marking task as completed: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: markTaskAsCompleted");
        }
    }

    /*In this we remove complete tasks
     * First we get all the tasks for particular user using userId
     * Then get the task
     * Then check the status to be completed or not
     * Then add the task in tasksToBeMarkedAsCompleted list
     * then send it to removeTasks from task dao*/
    @Override
    public String removeCompletedTasks(int userId) throws CompletedTasksNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: removeCompletedTasks");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            List<Task> tasksToBeRemoved = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getStatus() == Status.COMPLETED) {
                    tasksToBeRemoved.add(task);
                }
            }
            taskDao.removeTasks(tasksToBeRemoved);
            if (tasksToBeRemoved.isEmpty()) {
                throw new CompletedTasksNotFoundException("Task not found !");
            }
            return "Task removed successfully !";
        } catch (Exception e) {
            logger.error("An error occurred during removing completed tasks: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: removeCompletedTasks");
        }
    }

    /*Hear we get all completed tasks
     * First we get all the tasks using userId
     * Then check the status to be completed or not
     * Then add the completed task in completedTasks list
     * return That List*/
    @Override
    public List<Task> getCompletedTasks(int userId) throws CompletedTasksNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: getCompletedTasks");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            List<Task> completedTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.getStatus() == Status.COMPLETED) {
                    completedTasks.add(task);
                }
            }
            if (completedTasks.isEmpty()) {
                throw new CompletedTasksNotFoundException("No task is completed yet !");
            } else {
                return completedTasks;
            }
        } catch (CompletedTasksNotFoundException e) {
            logger.error("An error occurred during fetching completed tasks: " + e.getMessage());
            throw new CompletedTasksNotFoundException("No task is completed yet !");
        } catch (Exception e) {
            logger.error("An error occurred during fetching completed tasks: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getCompletedTasks");
        }
    }


    /*In this method, we can edit the tasks from our task list
     * For that first we get the tasks from the user's id
     *  Then check that the matching task is found in the list or not
     * If found then update it
     * also this method throws the custom exception called TaskNotFoundException if the task is not found in the list*/
    @Override
    public String editTask(int userId, String title, Task updatedTask) throws TaskNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: editTask");
            List<Task> tasks = taskDao.getTasksForUser(userId);
            boolean flag = false;
            for (Task task : tasks) {
                if (task.getTitle().equals(title)) {
                    logger.info("Updating the task details");
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setPriority(updatedTask.getPriority());
                    task.setDueDate(updatedTask.getDueDate());
                    taskDao.saveUpdatedTask(task);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new TaskNotFoundException("Task not found !");
            }
            return "Successfully Updated!";
        } catch (Exception e) {
            logger.error("An error occurred during editing a task: " + e.getMessage());
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: editTask");
        }
    }

    /*In this method we can show the status of particular task
     * */
    @Override
    public Status showStatus(int userId, String title) {
        try {
            logger.info("START :: CLASS :: UserHandlerImpl :: METHOD :: showStatus :: USER_ID :: " + userId);
            User user = userDao.getUserById(userId);
            if (user != null) {
                List<Task> tasksForUser = taskDao.getTasksForUser(userId);
                for (Task task : tasksForUser) {
                    if (task.getTitle().equals(title)) {
                        return task.getStatus();
                    }
                }
                throw new TaskNotFoundException("This task is not present in your list....");
            } else {
                throw new UserNotFoundException("Invalid user!");
            }
        } catch (TaskNotFoundException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            logger.error("An error occurred during showing task status: " + e.getMessage(), e);
            throw new RuntimeException("Internal server error", e);
        } finally {
            logger.info("END :: CLASS :: UserHandlerImpl :: METHOD :: showStatus");
        }
    }

    @Override
    public Task getTaskDetails(int userId, int taskId) throws TaskNotFoundException {
        try {
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: getTaskDetails");
            Task task = taskDao.findById(taskId);
            return task;
        }catch (TaskNotFoundException e) {
            logger.error("An error occurred while fetching task details: "+"," + e.getMessage());
            throw e;
        }catch (Exception e) {
            logger.error("An error occurred while fetching task details: " + e.getMessage());
            throw new RuntimeException("Error fetching task details", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getTaskDetails");
        }
    }

    @Override
    public String deleteTask(int taskId) {
        try{
            logger.info("START :: CLASS :: TaskHandlerImpl :: METHOD :: deleteTask");
            taskDao.deleteTask(taskId);
            return "Task deleted successfully !";
        }catch(TaskNotFoundException e){
            logger.error("An error occurred while fetching task details: " + e.getMessage());
            throw e;
        }catch (Exception e) {
            logger.error("An error occurred while fetching task details: " + e.getMessage());
            throw new RuntimeException("Error fetching task details", e);
        } finally {
            logger.info("END :: CLASS :: TaskHandlerImpl :: METHOD :: getTaskDetails");
        }
    }
}
