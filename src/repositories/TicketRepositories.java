package src.repositories;

import src.Database;
import src.errors.ErrorHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static src.repositories.MovieRepository.getMovieById;

public class TicketRepositories {
    public static List<String> getReservedSeatsBySession(int sessionId) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM reserved_seats WHERE session_id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, sessionId);
            ResultSet rs = stmt.executeQuery();

            List<String> reservedSeatsList = new ArrayList<>();

            while(rs.next()) {
                reservedSeatsList.add(rs.getString("chair"));
            }

            return reservedSeatsList;
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "getReservedSeatsBySession1", e);
            return null;
        }
    }

    public static boolean reserveSeats(List<String> seats, int sessionId) {
        Connection con = Database.getConnection();
        String query = "INSERT INTO reserved_seats (session_id, chair) VALUES (?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);

            for (String chair : seats) {
                stmt.setInt(1, sessionId);
                stmt.setString(2, chair);

                stmt.executeUpdate();
            }

            return true;
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "reserveSeats", e);
            return false;
        }
    }
}
