package src.entities;

public class Person {
    private String name;
    private String username;
    private String pin;
    private int role;

    public Person(String name, String username, String pin, int role) {
        this.name = name;
        this.username = username;
        this.pin = pin;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
