package src.repositories;

import src.Database;
import src.entities.Session;
import src.errors.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository {
    public static List<Session> getSessionsByCinema(int cinemaId) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM sessions WHERE cinema_id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, cinemaId);
            ResultSet rs = stmt.executeQuery();

            List<Session> sessionsList = new ArrayList<>();

            while(rs.next()) {
                Session session = new Session(
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getDouble("value"),
                        rs.getInt("id"),
                        rs.getInt("movie_id"),
                        rs.getInt("cinema_id")
                );

                sessionsList.add(session);
            }

            return sessionsList;
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "getSessionsByCinema", e);
            return null;
        }
    }

    public static Session getSessionById(int sessionId) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM sessions WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, sessionId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Session(
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getDouble("value"),
                    rs.getInt("id"),
                    rs.getInt("movie_id"),
                    rs.getInt("cinema_id")
            );
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "getSessionById", e);
            return null;
        }
    }

    public static boolean createSession(Session session) {
        Connection con = Database.getConnection();
        String query = "INSERT INTO sessions (movie_id, cinema_id, value, date) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, session.getMovieId());
            stmt.setInt(2, session.getCinemaId());
            stmt.setDouble(3, session.getValue());
            stmt.setTimestamp(4, Timestamp.valueOf(session.getDate()));
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "createSession", e);
            return false;
        }
    }

    public static boolean deleteSession(int sessionId) {
        Connection con = Database.getConnection();
        String query = "DELETE FROM session WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, sessionId);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "deleteSession", e);
            return false;
        }
    }
}
