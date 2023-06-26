package org.campusmolndal;

class Todo { //
    private int id;
    private String text;
    private boolean done;
    private int assignedTo;

    public Todo(int id, String text, boolean done, int assignedTo) { // Constructor for Todo
        this.id = id;
        this.text = text;
        this.done = done;
        this.assignedTo = assignedTo;
    }
    // tog bort id från konstruktorn

    // Getters and setters for Todo
    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }
}
