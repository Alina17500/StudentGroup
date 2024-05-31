package ru.vsu.cs.volobueva;

public class Student {
    private int id;
    private String name;
    private String gender;
    private int age;
    private int groupId;

    public Student(int id, String name, String gender, int age, int groupId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.groupId = groupId;
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
}
