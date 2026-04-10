package com.student;

import javafx.beans.property.SimpleStringProperty;

public class Student {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty course;

    public Student(String id, String name, String course) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.course = new SimpleStringProperty(course);
    }

    public String getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getCourse() { return course.get(); }
}