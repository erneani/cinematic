package src.entities;

import java.time.LocalDateTime;

public class Movie  {
    private LocalDateTime releaseDate;
    private String title;
    private String lore;
    private int id;

    public Movie(LocalDateTime releaseDate, String title, String lore, int id) {
        this.releaseDate = releaseDate;
        this.title = title;
        this.lore = lore;
        this.id = id;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
