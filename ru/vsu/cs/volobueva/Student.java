package ru.vsu.cs.volobueva;

public class Student {
    private int id;
    private String name;
    private String gender;
    private int age;
    private int groupId;
    private boolean task1;
    private boolean task2;
    private boolean task3;

    public Student(int id, String name, String gender, int age, int groupId, boolean task1, boolean task2, boolean task3) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.groupId = groupId;
        this.task1 = task1;
        this.task2 = task2;
        this.task3 = task3;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return name;
    }

    public void setFullName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean getTask1() {
        return task1;
    }
    public boolean getTask2() {
        return task2;
    }
    public boolean getTask3() {
        return task3;
    }

    public void setTask1(boolean task1) {
        this.task1 = task1;
    }
    public void setTask2(boolean task2) {
        this.task2 = task2;
    }
    public void setTask3(boolean task3) {
        this.task3 = task3;
    }
}
