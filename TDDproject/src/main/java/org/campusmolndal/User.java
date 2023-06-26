package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

class User {
    private int id;
    private String name;
    private int age;
    private List<List<Todo>> userTodos;

    public User(int id, String name, int age) { // Constructor for User
        this.id = id;
        this.name = name;
        this.age = age;
        this.userTodos = new ArrayList<List<Todo>>();
    }

    // Getters and setters for User
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setUserTodos(List<Todo> userTodos) {
        this.userTodos.add(userTodos);
    }
}
