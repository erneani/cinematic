package src.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private LocalDateTime date;
    private List<Integer> reservedSeats;
    private double value;
    private int id;
    private int movieId;
    private int cinemaId;

    public Session(LocalDateTime date, double value, int id, int movieId, int cinemaId) {
        this.date = date;
        this.reservedSeats = new ArrayList<Integer>();
        this.value = value;
        this.id = id;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Integer> getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(List<Integer> reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
