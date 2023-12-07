package HospitalManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;

    public Doctor(Connection connection) {
        this.connection = connection;
    }

    public void viewDoctors() {
        String query = "select * from   Doctors;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("********________*****___________****************________****************");
            System.out.println("| doctor id  | name                 |specialization |");
            System.out.println("********________*****___________****************________****************");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("| %-11d| %-20s| %-15s|\n", id, name, specialization);
            }
            System.out.println("********________*****___________****************________****************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getDoctorId(int id) {
        String query = "SELECT * FROM Doctors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a record with the given ID exists, false otherwise
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return false; // Return false in case of any exception or error
        }
    }
}
