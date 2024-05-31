package ru.vsu.cs.volobueva;

public class Main {

    public static void main(String[] args) {
        try {
            //Class.forName("org.postgresql.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
