package org.campusmolndal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbHandler {
    private TodoApp todoApp;
    private String dbName = "TodoList";
    public Connection conn;  // När vi kopplar upp oss skapas en session som hålls levande tills man stänger den man måste stänga den annars överhettas servern!



    public dbHandler() {
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + this.dbName + ".db");
            System.out.println("The Database " + this.dbName + " is live!");
            this.todoApp = new TodoApp();

            if (checkIfExists()) {
                loadFromDatabase();
                System.out.println("Database loaded Successfully");
            } else {
                firstSetUp(todoApp);
                System.out.println("Database created Successfully");

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with the database connection");
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public boolean checkIfExists() {
        boolean exists = false;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND (name='Users' OR name='ToDoLists')");

            exists = rs.next();
            System.out.println("Table exists: " + exists);

        } catch (SQLException e) {
            System.out.println("Error, could not check if tables exist");
            System.out.println(e.getMessage());
        }
        return exists;
    }

    public void loadFromDatabase() {
        List<User> users = loadUsersFromDatabase();
        List<Todo> todos = loadTodosFromDatabase();

        todoApp.setUsers(users);
        todoApp.setTodos(todos);

    }

    public List<User> loadUsersFromDatabase() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                User user = new User(id, name, age);
                System.out.println("User loaded: " + user.getName());
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Failed to load users from the database.");
            System.out.println(e.getMessage());
        }
        return users;
    }
    public List<Todo> loadTodosFromDatabase() {
        List<Todo> todos = new ArrayList<>();
        String query = "SELECT * FROM ToDoLists";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String text = resultSet.getString("text");
                boolean done = resultSet.getBoolean("done");
                int assignedTo = resultSet.getInt("assignedTo");
                Todo todo = new Todo(id, text, done, assignedTo);
                System.out.println("Todo loaded: " + todo.getText());
                todos.add(todo);
            }
        } catch (SQLException e) {
            System.out.println("Failed to load todos from the database.");
            System.out.println(e.getMessage());
        }
        return todos;
    }

    public void firstSetUp(TodoApp todoApp) {
        this.todoApp = todoApp;
        createUsersTable();
        createToDoListsTable();
    }


    private void createUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                    "DBid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id INTEGER NOT NULL," +
                    "name TEXT NOT NULL," +
                    "age INTEGER NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Users table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create Users table.");
            System.out.println(e.getMessage());
        }
    }
    private void createToDoListsTable() {
        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS ToDoLists (" +
                    "DBid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id INTEGER NOT NULL," +
                    "text TEXT NOT NULL," +
                    "done BOOLEAN NOT NULL," +
                    "assignedTo INTEGER NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("ToDoLists table created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create ToDoLists table.");
            System.out.println(e.getMessage());
        }
    }
    public void updateUserTable(TodoApp todoApp) {
        this.todoApp = todoApp;
        try (Statement stmt = conn.createStatement()) {
                for (int i = 1; i <= this.todoApp.getUsers().size(); i++){ // Loops through all users
                    if(this.todoApp.findUserById(i) != null){
                        int id = this.todoApp.findUserById(i).getId();
                        String userName = this.todoApp.findUserById(i).getName(); // Gets the name of the user
                        int userAge = this.todoApp.findUserById(i).getAge(); // Gets the age of the user


                    String checkUserSql = "SELECT * FROM Users WHERE id=" + id; // Query to check if the user already exists
                    ResultSet resultSet = stmt.executeQuery(checkUserSql);


                    if (!resultSet.next()) { // If the user doesn't exist, insert them into the database
                        String insertUserSql = "INSERT INTO Users (id, name, age) VALUES (" + id + ",'" + userName + "'," + userAge + ")";
                        stmt.executeUpdate(insertUserSql);
                    }

                    }
                }
        } catch (SQLException e) {
                    System.out.println("Failed to add user to the database.");
                    System.out.println(e.getMessage());
        }
    }

    public void updateToDoListsTable(TodoApp todoApp) {
        this.todoApp = todoApp;
        try (Statement stmt = conn.createStatement()) {

            for (int i = 1; i <= this.todoApp.getTodos().size(); i++){ // Loops through all to-do lists
                if(this.todoApp.findTodoById(i) != null){
                    int id = this.todoApp.findTodoById(i).getId(); // Gets the id of the user that the to-do list belongs to
                    String listName = this.todoApp.findTodoById(i).getText(); // Gets the name of the to-do list
                    boolean done = this.todoApp.findTodoById(i).isDone(); // Gets the status of the to-do list
                    int assignedTo = this.todoApp.findTodoById(i).getAssignedTo(); // Gets the name of the user that the to-do list belongs to

                    String checkUserSql = "SELECT * FROM ToDoLists WHERE id=" + id; // Query to check if the user already exists
                    ResultSet resultSet = stmt.executeQuery(checkUserSql);


                    if (!resultSet.next()) { // If the user doesn't exist, insert them into the database
                        String insertListSql = "INSERT INTO ToDoLists (id, text, done, assignedTo) VALUES " +
                                "(" + id + ",'" + listName + "'," + done + "," + assignedTo + ")"; // Inserts the to-do list into the database
                        stmt.executeUpdate(insertListSql);
                    }
                 }
           }
        }
        catch (SQLException e) {
            System.out.println("Failed to add to-do list to the database.");
            System.out.println(e.getMessage());
        }
    }
}


