package ru.vsu.cs.volobueva;

import database.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private ConnectionFactory connectionFactory;

    public DatabaseManager(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        createTables();
    }

    private void createTables() {
        createGroupsTable();
        createStudentsTable();
    }

    private void createGroupsTable() {
        String query = "CREATE TABLE IF NOT EXISTS groups1 (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "number INT)";
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createStudentsTable() {
        String query = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "gender VARCHAR(10)," +
                "age INT," +
                "group_id INT," +
                "FOREIGN KEY (group_id) REFERENCES groups1(id)," +
                "Task_1 BOOLEAN," +
                "Task_2 BOOLEAN," +
                "Task_3 BOOLEAN)";
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGroup(Group group) {
        String query = "INSERT INTO groups1 (id, name, number) VALUES (?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getName());
            preparedStatement.setInt(3, group.getNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editGroup(int id, Group group) {
        String query = "UPDATE groups1 SET name = ?, number = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getNumber());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(int id) {
        deleteStudentsByGroupId(id);
        String query = "DELETE FROM groups1 WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (id, name, gender, age, group_id, Task_1, Task_2, Task_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFullName());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getGroupId());
            preparedStatement.setBoolean(6, student.getTask1());
            preparedStatement.setBoolean(7, student.getTask2());
            preparedStatement.setBoolean(8, student.getTask3());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editStudent(int id, Student student) {
        String query = "UPDATE students SET name = ?, gender = ?, age = ?, group_id = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setInt(4, student.getGroupId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void passedTasks(int id, Student student) {
        String query = "UPDATE students SET Task_1 = ?, Task_2 = ?, Task_3 = ? WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, student.getTask1());
            preparedStatement.setBoolean(2, student.getTask2());
            preparedStatement.setBoolean(3, student.getTask3());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM groups1";
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int number = resultSet.getInt("number");
                groups.add(new Group(id, name, number));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection connection = connectionFactory.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int groupId = resultSet.getInt("group_id");
                boolean task1 = resultSet.getBoolean("Task_1");
                boolean task2 = resultSet.getBoolean("Task_2");
                boolean task3 = resultSet.getBoolean("Task_2");
                students.add(new Student(id, name, gender, age, groupId, task1, task2, task3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students WHERE group_id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int group_id = resultSet.getInt("group_id");
                boolean task1 = resultSet.getBoolean("Task_1");
                boolean task2 = resultSet.getBoolean("Task_2");
                boolean task3 = resultSet.getBoolean("Task_2");
                students.add(new Student(id, name, gender, age, group_id, task1, task2, task3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private void deleteStudentsByGroupId(int groupId) {
        String query = "DELETE FROM students WHERE group_id = ?";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, groupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
