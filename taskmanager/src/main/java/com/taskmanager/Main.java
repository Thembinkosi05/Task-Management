package com.taskmanager;

import com.taskmanager.dao.UserDao;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.view.*;
import com.taskmanager.util.HibernateUtil;
import com.taskmanager.service.TaskService;

import java.util.Scanner;
import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TaskService taskService = new  TaskService();
    private static UserDao userDao = new UserDao();
    private static User loggedInUser = new User();
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    if (loginUser()) {
                        runTaskManager();
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

       }
    private static void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        userDao.saveUser(user);
        loggedInUser = user;
        System.out.println("User registered successfully!");
    }
    private static boolean loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful!");
            runTaskManager();
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }
    private static void runTaskManager() {
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
                    Date dueDate = Date.valueOf(scanner.nextLine());

                    System.out.print("Enter task priority (LOW, MEDIUM, HIGH): ");
                    Task.Priority priority = Task.Priority.valueOf(scanner.nextLine().toUpperCase());

                    task.setTitle(title);
                    task.setDescription(description);
                    task.setPriority(priority);
                    task.setDueDate(dueDate);
                    task.setUserId(loggedInUser.getId());  //foregn key to know this task belong to which user.
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
                for (Task taskstring : taskService.getAllTasks(loggedInUser.getId())) {
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

        /*add search task to the menu */   
        mainMenu.addChoice(new MenuChoice() {
            @Override
            public String getText() {
                return "Search Task";
            }

            @Override
            public void run() {
                System.out.println("Enter task to search:  ");
                String searchWord = scanner.nextLine();
                List<Task> searchedList = taskService.searchTasks(searchWord);
                if (!searchedList.isEmpty()){
                    for (Task task : searchedList) {
                        System.out.println(task);
                    }
                }
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
