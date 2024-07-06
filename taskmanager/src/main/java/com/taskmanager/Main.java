package com.taskmanager;

import com.taskmanager.model.Task;
import com.taskmanager.view.*;
import com.taskmanager.util.HibernateUtil;
import com.taskmanager.service.TaskService;

import java.util.Scanner;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskService taskService = new  TaskService();
    public static void main(String[] args) {
        
        Menu mainMenu = new Menu("Task Management System", null);

        /*add task to the main menu */
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "Add Task";
            }

            @Override
            public void run() {
                System.out.println();
                Task task = new Task();
        
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter task due date (YYYY-MM-DD): ");
                   // Date dueDate = Date.valueOf(scanner.nextLine());

                    System.out.print("Enter task priority (LOW, MEDIUM, HIGH): ");
                    Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());

                    task.setTitle(title);
                    task.setDescription(description);
                    task.setPriority(priority);
                   // task.setDueDate(dueDate);
                    taskService.saveTask(task);
                    
                System.out.println("Add task added to the main menu");
                System.out.println();
            }
        });
        
        /*add view all tasks to the main menu */
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "View all tasks";
            }

            @Override
            public void run() {
                for (Task taskstring : taskService.getAllTasks()) {
                    System.out.println(taskstring);
                    
                }

            }
        });
        
        /*add update tasks to the main menu */
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "Update Task";
            }

            @Override
            public void run() {
                
            }
        });
        
        /*add delete task to the manin menu */
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "Delete Task";
            }

            @Override
            public void run() {

            }
        });

        /*add Exit to the main menu*/
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "Exit";
            }

            @Override
            public void run() {
                taskService.Exit();
            }
        });
    
        mainMenu.run();
        }
}
