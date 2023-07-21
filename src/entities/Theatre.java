package src.entities;

import src.entities.Session;

import java.util.List;

public class Theatre {
    private String name;
    private String location;
    private int id;

    public Theatre(String name, String location, int id) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
