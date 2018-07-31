package pl.sda.fitapp.domains.dto;

import pl.sda.fitapp.domains.ActivityType;
import pl.sda.fitapp.domains.Place;
import pl.sda.fitapp.domains.Trainer;

import java.time.LocalDateTime;

public class ActivityEditDto {
    private Trainer trainer;
    private Place place;
    private LocalDateTime startTime;
    private int duration;
    private int maxParticipants;
    private double price;
    private String description;
    private ActivityType activityType;

    public ActivityEditDto() {
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }


}
