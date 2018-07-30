package pl.sda.fitapp.domains;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainerId;
    private Long objectId;
    private LocalDateTime startTime;
    private int duration;
    private int maxParticipants;
    private double price;
    private String description;
    private ActivityType activityType;

    @ManyToMany
    private List<GymUser> gymUsers;

    public Activity(Long trainerId, Long objectId, LocalDateTime startTime, int duration, int maxParticipants, double price, String description, ActivityType activityType) {
        this.trainerId = trainerId;
        this.objectId = objectId;
        this.startTime = startTime;
        this.duration = duration;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.description = description;
        this.activityType = activityType;
    }

    public Activity() {
    }

    public void addGymUserToList(GymUser gymUser){
        gymUsers.add(gymUser);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
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

    public List<GymUser> getGymUsers() {
        return gymUsers;
    }

    public void setGymUsers(List<GymUser> gymUsers) {
        this.gymUsers = gymUsers;
    }


}
