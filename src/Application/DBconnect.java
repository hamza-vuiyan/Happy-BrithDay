package Application;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBconnect {
    private static final String URL = "jdbc:mysql://b3p5m8nhncgiqy1daeus-mysql.services.clever-cloud.com:3306/b3p5m8nhncgiqy1daeus";
    private static final String USER = "urowmyrks8sa1rih";
    private static final String PASSWORD = "RsKRqsn19uGmkebas6WR";

    static String message;

    public static String insertStudentData(String studentId, String name, String dateOfBirth) {
        String insertQuery = "INSERT INTO classmates (ID, name, dateOfBirth) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, dateOfBirth);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                message = "A new student was inserted successfully!";
               // System.out.println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            message = e.getMessage();
            // System.err.println("Failed to insert data. Error: " + e.getMessage());
        }
        return message;
    }


    public static String deleteStudentData(String studentId) {
        String deleteQuery = "DELETE FROM classmates WHERE ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setString(1, studentId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                return "Student data deleted successfully!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Failed to delete student data.";
    }

    public static String updateStudentData(String studentId, String name, String dateOfBirth) {
        String updateQuery = "UPDATE classmates SET name = ?, dateOfBirth = ? WHERE ID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, dateOfBirth);
            preparedStatement.setString(3, studentId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                return "Student data updated successfully!";
            }
        } catch (SQLException e) {
            return e.getMessage();
        }
        return "Failed to update student data.";
    }

    
}
