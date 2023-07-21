package src.entities;

public class ReservedSeat {
    private int id;
    private int sessionId;
    private String chair;

    public ReservedSeat(int id, int sessionId, String chair) {
        this.id = id;
        this.sessionId = sessionId;
        this.chair = chair;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }
}
