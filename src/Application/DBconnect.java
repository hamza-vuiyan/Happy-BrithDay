package Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import Application.ModelClass.Students;


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

    static ArrayList<String> l = new ArrayList<String>();

    public static ArrayList<String> searchStudent(String ID){
        String searchQuery = "SELECT * FROM classmates WHERE ID = ?";
       
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setString(1, ID);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dateOfbirth");
                l.add(name);
                l.add(dob);

                return l;
            } else {
                // result = "No student found with ID: " + ID;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            // e.printStackTrace();
        }
        return l;
    }

    public static ArrayList<Students> viewStudents(){
        ArrayList<Students> studentList = new ArrayList<>();

        String viewQuery = "SELECT * FROM classmates";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format if needed
        LocalDate currentDate = LocalDate.now();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(viewQuery);
             java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("name");
                String dateOfBirthString = resultSet.getString("dateOfBirth");

                try {
                    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, formatter);
                    LocalDate nextBirthday = dateOfBirth.withYear(currentDate.getYear());

                    // If the birthday has already passed this year, add a year
                    if (nextBirthday.isBefore(currentDate) || nextBirthday.isEqual(currentDate)) {
                        nextBirthday = nextBirthday.plusYears(1);
                    }

                    // Check if the birthday is within the upcoming year
                    if (nextBirthday.isAfter(currentDate)) {
                        Students student = new Students(id, name, dateOfBirthString);
                        studentList.add(student);
                    }
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing date: " + dateOfBirthString + ".  Skipping student.");
                    // Handle the date parsing error (e.g., log it, skip the student)
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to retrieve data. Error: " + e.getMessage());
        }
        return studentList;
    }
}
