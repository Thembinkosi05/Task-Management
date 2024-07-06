package com.taskmanager.service;

import com.taskmanager.dao.TaskDao;
import com.taskmanager.model.Task;

import java.util.List;

public class TaskService {

    private TaskDao taskDao;

    public TaskService() {
        taskDao = new TaskDao();
    }

    public void saveTask(Task task) {
        taskDao.saveTask(task);
    }

    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void deleteTask(int id) {
        taskDao.deleteTask(id);
    }

    public void Exit(){
        taskDao.Exit();
        System.exit(0);
    }
}