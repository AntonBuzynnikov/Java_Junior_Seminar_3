package dataBase;

import java.sql.*;
import java.util.Properties;
import java.util.Stack;

public class dbOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "anton_lection_4";
    private static final String PASSWORD = "some_password";
    private static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createTable(){
        try (Statement statement = getConnection().createStatement()){
            statement.execute("DROP TABLE Student;");
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS Student(
                          id INT NOT NULL AUTO_INCREMENT,
                          first_name VARCHAR(45),
                          second_name VARCHAR(45),
                          age INT,
                          PRIMARY KEY(id)
                          );
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertStudent(){
        try(Statement statement = getConnection().createStatement()) {
            statement.execute("""
                    INSERT INTO Student (first_name,second_name,age) VALUES
                    ('Anton','Buzynnikov',28),
                    ('Ivan','Ivanov',21),
                    ('Sergey','Sidorov',31),
                    ('Alexey','Petrov',22),
                    ('Petr','Volkov',23),
                    ('Alexander','Svitov',46)
                    """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void selectStudent(int id){
        try(Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student WHERE id >= " + id);
            while (resultSet.next()){
                int number = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                int age = resultSet.getInt("age");
                System.out.println("id: " + number + ", first_name: " + firstName + ", second_name: " + secondName +
                        ", age: " + age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateStudent(String firstName, int age){
        try(PreparedStatement statement = getConnection().prepareStatement("UPDATE Student SET age = ? WHERE " +
                "first_name = ?")){
            statement.setString(1, String.valueOf(age));
            statement.setString(2,firstName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteStudent(int id){
        try(PreparedStatement statement = getConnection().prepareStatement("DELETE FROM Student WHERE id = ?")){
            statement.setString(1, String.valueOf(id));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
