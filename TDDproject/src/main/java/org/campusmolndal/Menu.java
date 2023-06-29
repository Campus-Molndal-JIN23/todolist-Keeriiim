package org.campusmolndal;

import java.util.Scanner;

public class Menu {
    private boolean online = true;
    private TodoApp todoApp;
    private dbHandler dbhandler;

    public Menu() {
        System.out.println("Welcome to the TodoApp!");
        this.todoApp = new TodoApp(); // Creates a new TodoApp each time the program is run, it will not reuse the same TodoApp
        this.dbhandler = new dbHandler(); // Creates a new dbHandler --- Nog bäst att ha den efter varje utfört arbete
        startMenu();
    }


    private void startMenu(){
        this.todoApp.setUsers(dbhandler.loadUsersFromDatabase());
        this.todoApp.setTodos(dbhandler.loadTodosFromDatabase());


        while(online){
            int id;
            int age;
            String name;
            String text;
            boolean done;
            int assignedTo;

            System.out.println("\nPlease choose an option:");
            System.out.println("1. Create ");
            System.out.println("2. Delete ");
            System.out.println("3. Update ");
            System.out.println("4. Show ");
            System.out.println("5. Exit ");

            Scanner scan = new Scanner(System.in);
            int mainOption = scan.nextInt();
            switch (mainOption){
                case 1: // Create
                    System.out.println("1. Create a new user");
                    System.out.println("2. Create a new todo");
                    int option_1 = scan.nextInt();

                    switch(option_1){
                        case 1: // Create a new user
                            System.out.println("Please enter the user ID:");
                            id = scan.nextInt();
                            System.out.println("Please enter the user name:");
                            scan.nextLine();
                            name = scan.nextLine();
                            System.out.println("Please enter the user age:");
                            age = scan.nextInt();
                            todoApp.createUser(id, name, age);
                            break;

                        case 2: // Create a new todo
                             System.out.println("Please enter the todo ID:");
                            id = scan.nextInt();
                            System.out.println("Please enter the todo text:");
                            scan.nextLine();
                            text = scan.nextLine();
                            System.out.println("Is it done? (true/false):");
                            done = scan.nextBoolean();
                            System.out.println("What ID should it be assigned to?:");
                            assignedTo = scan.nextInt();
                            todoApp.createTodo(id, text, done, assignedTo);
                            break;
                       }
                       break; // End of case 1 main menu

                case 2: // Delete
                    System.out.println("1. Delete an user");
                    System.out.println("2. Delete all users");
                    System.out.println("3. Delete a todo");
                    System.out.println("4. Delete all todos");

                    int option_2 = scan.nextInt();

                    switch(option_2){

                        case 1: // Delete an User
                            System.out.println("Please enter the user ID:");
                            id = scan.nextInt();
                            todoApp.deleteUserById(id);
                            break;

                        case 2: // Delete all users
                            this.todoApp.setUsers(todoApp.deleteAllUsers());
                            break;


                        case 3: // Delete a todo
                            System.out.println("Please enter the todo ID:");
                            id = scan.nextInt();
                            todoApp.deleteTodoById(id);
                            break;

                        case 4: // Delete all todos
                            this.todoApp.setTodos(todoApp.deleteAllTodos());
                            break;
                    }
                    break; // End of case 2 main menu

                case 3: // Update
                    System.out.println("1. Update an user");
                    System.out.println("2. Update a todo");
                    int option_3 = scan.nextInt();
                switch(option_3){
                    case 1: // Update an user
                        System.out.println("Please enter the user ID you want to update:");
                        id = scan.nextInt();
                        System.out.println("Please enter the new user name:");
                        scan.nextLine();
                        name = scan.nextLine();
                        System.out.println("Please enter the new user age:");
                        age = scan.nextInt();
                        todoApp.updateUser(id, name, age);
                        break;

                    case 2: // Update a todo
                        System.out.println("Please enter the todo ID you want to update:");
                        id = scan.nextInt();
                        System.out.println("Please enter the new todo text:");
                        scan.nextLine();
                        text = scan.nextLine();
                        System.out.println("Is it done? (true/false):");
                        done = scan.nextBoolean();
                        System.out.println("What ID should it be assigned to?:");
                        assignedTo = scan.nextInt();
                        todoApp.updateTodoById(id, text, done, assignedTo);
                        break;
                }
                break; // End of case 3 main menu

                case 4: // Show
                    System.out.println("1. Show an user");
                    System.out.println("2. Show all users");
                    System.out.println("3. Show a todo");
                    System.out.println("4. Show all todos");
                    System.out.println("5. Show all todos for a specific user");
                    int option_4 = scan.nextInt();

                    switch(option_4){
                        case 1: // Show a user from ID
                        System.out.println("Please enter the user ID:");
                        id = scan.nextInt();
                        todoApp.showUserById(id);
                        break;

                        case 2: // Show all users
                        todoApp.showAllUsers();
                        break;

                        case 3: // Show a todo from ID
                            System.out.println("Please enter the todo ID:");
                            id = scan.nextInt();
                          todoApp.showTodoById(id);
                            break;

                        case 4: // Show all todos
                           todoApp.showAllTodos();
                            break;

                        case 5: // Show all todos for a specific user
                            System.out.println("Please enter the user ID:");
                            id = scan.nextInt();
                            todoApp.showUsersTodos(id);
                            break;
                    }
                    break; // End of case 4 main menu


                case 5: // Exit
                    online = false;
                    System.out.println("Thank you for using the TodoApp!");
                    dbhandler.updateUserTable(todoApp);
                    dbhandler.updateToDoListsTable(todoApp);
                    break;
            }
        }
    }
}
