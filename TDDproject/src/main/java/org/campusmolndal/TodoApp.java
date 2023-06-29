package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

class TodoApp {
    private List<User> users; // List that contains all users
    private List<Todo> todos; // List that contains all todos

    public TodoApp() { // Constructor for TodoApp that creates empty lists
        users = new ArrayList<>();
        todos = new ArrayList<>();
    }

    public List getUsers() {
        return users;
    }

    public List getTodos() {
        return todos;
    }


    // Create
    public void createUser(int id, String name, int age) {
        User user = new User(id, name, age);
        if (findUserById(id) != null) {

            System.out.println("User already exists with ID: " + id);
            System.out.println("Last added ID: " + getUsers().size());

        } else if (findUserByName(name) != null) {
            System.out.println("User already exists with name: " + name);

        } else {
            users.add(user);
            System.out.println("New user created: " + user.getName());
        }

    }

    public void createTodo(int id, String text, boolean done, int assignedTo) { // Method that creates a new Todo
        Todo todo = new Todo(id, text, done, assignedTo); // Creates a new Todo
        todos.add(todo); // Adds the new Todo to the list of todos
        System.out.println("New TODO created: " + todo.getText()); // Prints out the text of the new Todo
    }

    // Delete

    public void deleteUserById(int id) {
        User user = findUserById(id); //
        if (user != null) {
            users.remove(user);
            System.out.println("User deleted with ID: " + id);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    public List deleteAllUsers() {
        users.clear();
        System.out.println("All users deleted");
        return users;
    }
    public void deleteTodoById(int id) {
        Todo todo = findTodoById(id);
        if (todo != null) {
            todos.remove(todo);
            System.out.println("Todo deleted with ID: " + id);
        } else {
            System.out.println("Todo not found with ID: " + id);
        }
    }

    public List deleteAllTodos() {
        todos.clear();
        System.out.println("All todos deleted");
        return todos;
    }



    // Read
    public User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        System.out.println("\n" + "-----------------------------");
        System.out.println("No user found with ID: " + id);
        System.out.println("-----------------------------");
        return null;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Todo findTodoById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        System.out.println("\n" + "-----------------------------");
        System.out.println("No todo found with ID: " + id);
        System.out.println("-----------------------------");
        return null;
    }


    // Update

    public void updateTodoText(int id, String newText) { // Method that updates the text of a Todo with a specific ID
        Todo todo = findTodoById(id);
        if (todo != null) {
            todo.setText(newText);
            System.out.println("Todo updated. New text: " + newText);
        } else {
            System.out.println("Todo not found with ID: " + id);
        }
    }

    public void updateTodoStatus(int id, boolean done) {
        Todo todo = findTodoById(id);
        if (todo != null) {
            todo.setDone(done);
            System.out.println("Todo updated. Done status: " + done);
        } else {
            System.out.println("Todo not found with ID: " + id);
        }
    }

    public void updateUser(int id, String newName, int newAge) { // Method that updates a User with a specific ID
        User user = findUserById(id);
        if (user != null) {

            user.setName(newName);
            user.setAge(newAge);
            System.out.println("User updated. New name: " + newName);
            System.out.println("User updated. New age: " + newAge);
        } else {
            System.out.println("User not found with ID: " + id);
        }
    }

    public void updateTodoById(int id, String newText, boolean done, int newAssigneed) {
        Todo todo = findTodoById(id);
        if (todo != null) {
            todo.setText(newText);
            todo.setDone(done);
            todo.setAssignedTo(newAssigneed);
            System.out.println("Todo updated. New text: " + newText);
            System.out.println("Todo updated. Done status: " + done);
            System.out.println("Todo updated. New assignee: " + newAssigneed);
        } else {
            System.out.println("Todo not found with ID: " + id);
        }
    }


    // Prints

    public void showAllTodos() { // Method that prints out all Todos
        if (todos.isEmpty()) {
            System.out.println("\n" + "--------------------");
            System.out.println("No todos found");
            System.out.println("--------------------");
        } else {
            System.out.println("All Todos:");
        for (Todo todo : todos) {
            System.out.println("ID: " + todo.getId());
            System.out.println("Text: " + todo.getText());
            System.out.println("Done: " + todo.isDone());
            System.out.println("Assigned to: " + todo.getAssignedTo());
            System.out.println();
        }
    }
    }

    public void showTodoById(int id) { // Method that prints out a Todo with a specific ID
        Todo todo = findTodoById(id);
        if (todo != null) {
            System.out.println("Todo ID: " + todo.getId());
            System.out.println("Text: " + todo.getText());
            System.out.println("Done: " + todo.isDone());
            System.out.println("Assigned to: " + todo.getAssignedTo());
        }
    }

    public void showAllUsers() {
        if(users.isEmpty() ){
            System.out.println("\n" + "--------------------");
            System.out.println("No users found");

            System.out.println("--------------------");
        }
        else {
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println();
        }
        }
    }

    public void showUserById(int id) {
        User user = findUserById(id);
        if (user != null) {
            System.out.println("User ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
        }
    }

    public void showUsersTodos(int id) {
        User user = findUserById(id);
        if (user != null) {
            System.out.println("Todos assigned to " + user.getName() + ":");
            for (Todo todo : todos) {
                if (todo.getAssignedTo() == id) {
                    System.out.println("ID: " + todo.getId());
                    System.out.println("Text: " + todo.getText());
                    System.out.println("Done: " + todo.isDone());
                    System.out.println();
                    user.setUserTodos(todos);
                }
            }
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
