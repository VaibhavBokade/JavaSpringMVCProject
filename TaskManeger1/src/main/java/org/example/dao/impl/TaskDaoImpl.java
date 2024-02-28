package org.example.dao.impl;

import org.example.dao.TaskDao;
import org.example.entities.Task;
import org.example.entities.User;
import org.example.exceptions.TaskNotFoundException;
import org.example.exceptions.UserNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*In this class we can all the methods which are works on our task entity and
 * Also helping method to fetch data of user according to user details*/
@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    /*In this method we can add the task for specific user
     *According to their userId we first get the user's taskList
     * By using query and add all the tasks in specific list*/
    @Override
    public Task addTaskForUser(int userId, Task task) throws UserNotFoundException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE id = :userId", User.class);
            query.setParameter("userId", userId);
            User user = query.uniqueResult();
            if (user == null) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            task.setUser(user);
            List<Task> taskList = user.getTaskList();
            taskList.add(task);
            user.setTaskList(taskList);
            session.getTransaction().commit();
            session.close();
            return task;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding task for user", e);
        }
    }

    /*In this we can get all the tasks for the particular user
     * According to their userId from database we retrieve tasks for user using query*/
    @Override
    public List<Task> getTasksForUser(int userId) throws UserNotFoundException {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query<Task> query = session.createQuery("FROM Task WHERE user.id = :userId", Task.class);
            query.setParameter("userId", userId);
            List<Task> tasks = query.getResultList();
            session.getTransaction().commit();
            return tasks;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching tasks for user", e);
        }
    }

    /*In this method we can search the task in our task list
     * By using task title we can find the task */
    @Override
    public List<Task> searchTasksByTitle(List<Task> tasks, String title) throws TaskNotFoundException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        return matchingTasks;
    }

    /*In this method we can search the task in our task list
     * By using task due date we can find the task */
    @Override
    public List<Task> searchTasksByDueDate(List<Task> tasks, String dueDate) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDueDate().equals(dueDate)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException("Task not found");
        }
        return matchingTasks;
    }

    /*In this method we remove the tasks from task list
     * we first traverse the list and check the task is present
     * Then we remove the task*/
    @Override
    public void removeTasks(List<Task> tasksToBeRemoved) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            for (Task task : tasksToBeRemoved) {
                session.delete(task);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    /*In this method we marked the task as completed
     * First get the task whom we want to mark as completed
     * Then update the status and commit the changes*/
    @Override
    public void markCompleted(List<Task> tasksToBeMarkedAsCompleted) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            for (Task task : tasksToBeMarkedAsCompleted) {
                session.update(task);
            }
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    /*In this method we can update all the changes that we want in our task to the database*/
    @Override
    public void saveUpdatedTask(Task updatedTask) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(updatedTask);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public Task findById(int taskId)throws TaskNotFoundException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, taskId);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching task by ID", e);
        }
    }

    @Override
    public void deleteTask(int taskId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                session.delete(task);
                session.getTransaction().commit();
            } else {
                throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
            }
        }catch (TaskNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching task by ID", e);
        }
    }
}

