package ru.vsu.cs.volobueva;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class StudentGroups {
    private DatabaseManager databaseManager;
    private List<Group> groups;
    private List<Group> students;

    public StudentGroups() {
        databaseManager = new DatabaseManager(() -> DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsdb", "root", "delFin92!"));
        groups = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addGroup(Group group) {
        databaseManager.addGroup(group);
    }

    public void editGroup(int id, Group group) {
        databaseManager.editGroup(id, group);
    }

    public void deleteGroup(int id) {
        databaseManager.deleteGroup(id);
    }

    public void addStudent(Student student) {
        databaseManager.addStudent(student);
    }

    public void editStudent(int id, Student student) {
        databaseManager.editStudent(id, student);
    }

    public void deleteStudent(int id) {
        databaseManager.deleteStudent(id);
    }

    public List<Group> getAllGroups() {
        return databaseManager.getAllGroups();
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        return databaseManager.getStudentsByGroupId(groupId);
    }

    public Group getGroupById(int id) {
        List<Group> allGroups = databaseManager.getAllGroups();
        for (Group group : allGroups) {
            if (group.getId() == id) {
                return group;
            }
        }
        throw new IllegalArgumentException("id " + id + " не найден");
    }

    public Student getStudentId(int id) {
        List<Student> allStudents = databaseManager.getAllStudents();
        for (Student students : allStudents) {
            if (students.getId() == id) {
                return students;
            }
        }
        throw new IllegalArgumentException("id " + id + " не найден");
    }
}
