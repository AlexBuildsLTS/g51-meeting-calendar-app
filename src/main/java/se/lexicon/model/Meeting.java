package se.lexicon.model;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    private int id; // Add this field to match the 'id' in the database
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<User> participants;

    // Constructor with ID
    public Meeting(int id, String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<User> participants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    // Constructor without ID (for creating new Meetings)
    public Meeting(String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<User> participants) {
        this(0, title, description, startTime, endTime, participants);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
