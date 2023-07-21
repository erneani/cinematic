package src.repositories;

import src.Database;
import src.entities.Theatre;
import src.errors.ErrorHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaRepository {
    public static Theatre getCinemaById(int cinemaId) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM cinemas WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cinemaId);
            ResultSet rs = stmt.executeQuery();

            return new Theatre(
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("id")
            );
        } catch (SQLException e) {
            ErrorHandler.handleError("ERROR", "getCinemaById", e);
            return null;
        }
    }
    public static List<Theatre> getAllCinemas() {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM cinemas";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Theatre> cinemaList = new ArrayList<Theatre>();

            while(rs.next()) {
                Theatre cinema = new Theatre(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("id")
                );

                cinemaList.add(cinema);
            }

            return cinemaList;
        } catch(SQLException e) {
            ErrorHandler.handleError("ERROR", "getAllCinemas", e);
            return null;
        }
    }

    public static boolean createCinema(Theatre cinema) {
        Connection con = Database.getConnection();
        String query = "INSERT INTO cinemas (name, location) VALUES (?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, cinema.getName());
            stmt.setString(2, cinema.getLocation());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "createCinema", e);
            return false;
        }
    }

    public static boolean deleteCinema(int cinemaId) {
        Connection con = Database.getConnection();
        String query = "DELETE FROM cinemas WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cinemaId);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "deleteCinema", e);
            return false;
        }
    }
}
