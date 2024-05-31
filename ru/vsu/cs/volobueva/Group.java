package ru.vsu.cs.volobueva;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;
    private int number;
    private List<Student> students;

    public Group(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.students = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
