package src.repositories;

import src.Database;
import src.entities.Movie;
import src.errors.ErrorHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    public static Movie getMovieById(int movieId) {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM movies WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            return new Movie(
                    rs.getTimestamp("releasedate").toLocalDateTime(),
                    rs.getString("title"),
                    rs.getString("lore"),
                    rs.getInt("id")
            );
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "getMovieById", e);
            return null;
        }
    }

    public static List<Movie> getAllMovies() {
        Connection con = Database.getConnection();
        String query = "SELECT * FROM movies";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Movie> movieList = new ArrayList<>();

            while(rs.next()) {
                movieList.add(new Movie(
                        rs.getTimestamp("releasedate").toLocalDateTime(),
                        rs.getString("title"),
                        rs.getString("lore"),
                        rs.getInt("id")
                ));
            }

            return movieList;
        } catch(SQLException e) {
            ErrorHandler.handleError("Error", "getMovieById", e);
            return null;
        }
    }

    public static boolean createMovie(Movie movie) {
        Connection con = Database.getConnection();
        String query = "INSERT INTO movies (releasedate, title, lore) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setTimestamp(1, Timestamp.valueOf(movie.getReleaseDate()));
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getLore());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "createMovie", e);
            return false;
        }
    }

    public static boolean deleteMovie(int movieId) {
        Connection con = Database.getConnection();
        String query = "DELETE FROM movies WHERE id=?";

        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, movieId);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            ErrorHandler.handleError("Error", "deleteMovie", e);
            return false;
        }
    }
}
