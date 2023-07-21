package src;

import src.logger.Logger;
import src.errors.ErrorHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost/cinematic";

        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            ErrorHandler.handleError("FATAL", "getConnection", e);
            throw new RuntimeException(e);
        }
    }

    public static void createTables() {
        Logger.info("Creating cinematic tables.");
        try {
            Database.createMovieTable();
            Database.createSessionTable();
            Database.createSeatTable();
            Database.createCinemaTable();
            Database.createUserTable();
        } catch(SQLException e) {
            ErrorHandler.handleError("ERROR", "createTables", e);
        }
    }

    private static void createMovieTable() throws SQLException {
        Logger.info("Creating Movie table.");
        Connection con = Database.getConnection();
        String movieQuery = "CREATE TABLE IF NOT EXISTS movies(" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(256)," +
                "lore VARCHAR(1024)," +
                "releaseDate TIMESTAMP" +
                ")";

        PreparedStatement stmt = con.prepareStatement(movieQuery);
        stmt.executeUpdate();
        stmt.close();
    }

    private static void createSessionTable() throws SQLException {
        Logger.info("Creating Session table.");
        Connection con = Database.getConnection();
        String sessionQuery = "CREATE TABLE IF NOT EXISTS sessions(" +
                "id SERIAL PRIMARY KEY," +
                "movie_id INT," +
                "cinema_id INT," +
                "value DECIMAL," +
                "date TIMESTAMP" +
                ")";

        PreparedStatement stmt = con.prepareStatement(sessionQuery);
        stmt.executeUpdate();
    }

    private static void createSeatTable() throws SQLException {
        Logger.info("Creating Reserved Seats table.");
        Connection con = Database.getConnection();
        String seatsQuery = "CREATE TABLE IF NOT EXISTS reserved_seats(" +
                "id SERIAL PRIMARY KEY," +
                "session_id INT," +
                "chair VARCHAR(8)" +
                ")";

        PreparedStatement stmt = con.prepareStatement(seatsQuery);
        stmt.executeUpdate();
    }

    private static void createCinemaTable() throws SQLException {
        Logger.info("Creating Cinema table.");
        Connection con = Database.getConnection();
        String cinemaQuery = "CREATE TABLE IF NOT EXISTS cinemas(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100)," +
                "location VARCHAR(300)" +
                ")";

        PreparedStatement stmt = con.prepareStatement(cinemaQuery);
        stmt.executeUpdate();
    }

    private static void createUserTable() throws SQLException {
        Logger.info("Creating User table.");
        Connection con = Database.getConnection();
        String userQuery = "CREATE TABLE IF NOT EXISTS users(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(124)," +
                "username VARCHAR(64)," +
                "pin VARCHAR(16)," +
                "role INT" +
                ")";

        PreparedStatement stmt = con.prepareStatement(userQuery);
        stmt.executeUpdate();
    }
}
