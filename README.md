# Task-Management
Overview
This Task Management System is a command-line application built using Java, Hibernate, and MySQL. It allows users to manage tasks with functionalities including adding, viewing, updating, and deleting tasks. Additionally, it features basic user authentication and task search capabilities.

Features
User Registration and Login: Secure user registration and login system.
Task Management: Add, view, update, and delete tasks.
Task Search: Search tasks based on keywords.
Task Prioritization: Categorize tasks by priority (LOW, MEDIUM, HIGH).
Tech Stack
Java: The primary programming language.
Hibernate: ORM tool for database operations.
MySQL: Database to store user and task information.

Getting Started
Prerequisites
Java 8/11
Maven
MySQL
Setup
Clone the repository:
bash
Copy code
git clone https://github.com/thembinkosi05/Task-Manager.git
cd task-manager
Configure the database:

Create a MySQL database and update the hibernate.cfg.xml file with your database credentials.

xml
Copy code
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/yourdatabase</property>
        <property name="hibernate.connection.username">yourusername</property>
        <property name="hibernate.connection.password">yourpassword</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="show_sql">true</property>
    </session-factory>
</hibernate-configuration>
Build the project:
bash
Copy code
mvn clean install
Run the application:
bash
Copy code
java -jar target/task-manager-1.0-SNAPSHOT.jar
Usage
User Registration
Select the "Register" option.
Enter your desired username and password.
User Login
Select the "Login" option.
Enter your registered username and password.
Task Management
Add Task:

Enter task title, description, due date (YYYY-MM-DD), and priority (LOW, MEDIUM, HIGH).
View All Tasks:

Displays a list of all tasks.
Update Task:

Enter the task ID to update.
Update task details as prompted.
Delete Task:

Enter the task ID to delete.
Task Search
Search Tasks:
Enter a keyword to search for tasks.
Contributing
Feel free to fork this repository and make your own changes. Contributions are welcome!


Acknowledgements
Java
Hibernate
MySQL
Maven