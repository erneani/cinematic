package src.repositories;

import src.Database;
import src.entities.Person;
import src.errors.ErrorHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersRepository {
    public static boolean checkUserAndPin(String username, String pin) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM users WHERE username=? and pin=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, pin);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch(SQLException e) {
            ErrorHandler.handleError("ERROR", "checkUserAndPin", e);
            return false;
        }
    }

    public static Person getUserByUsername(String username) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM users WHERE username=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Person(
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("pin"),
                    rs.getInt("role")
            );
        } catch(SQLException e) {
            ErrorHandler.handleError("ERROR", "getUserByUsername", e);
            return null;
        }
    }

    public static boolean checkIfAdminExists() {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM users WHERE role=1";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch(SQLException e) {
            return false;
        }
    }

    public static boolean insertUser(Person user) {
        Connection con = Database.getConnection();
        String query = "INSERT INTO users (name, username, pin, role) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPin());
            stmt.setInt(4, user.getRole());

            stmt.executeUpdate();

            return true;
        } catch(SQLException e) {
            ErrorHandler.handleError("ERROR", "insertUser", e);
            return false;
        }
    }
}
