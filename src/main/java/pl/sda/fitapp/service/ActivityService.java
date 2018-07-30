package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.Activity;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.dto.ActivityEditDto;
import pl.sda.fitapp.repositories.ActivityRepository;
import pl.sda.fitapp.repositories.GymUserRepository;

import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private GymUserRepository gymUserRepository;

    public Long addActivity(Activity activity) {
        activityRepository.save(activity);
        return activity.getId();
    }


    public Optional<Activity> addUserToActivity(Long idUser, Long idActivity) {
        Optional<GymUser> foundUser = gymUserRepository.findById(idUser);
        Optional<Activity> foundActivity = activityRepository.findById(idActivity);

        if (foundUser.isPresent() && foundActivity.isPresent()) {
            GymUser gymUser = foundUser.get();
            Activity activity = foundActivity.get();

            if (gymUser != null && activity != null) {
                activity.addGymUserToList(gymUser);
            }

            activity = activityRepository.save(activity);

            return Optional.of(activity);
        }
        return Optional.empty();
    }

    public Optional<Activity> editActivity(Long id, ActivityEditDto editDto){
        Optional<Activity> foundActivity = activityRepository.findById(id);

        if (activityRepository.findById(id).isPresent()) {
            Activity activity = foundActivity.get();

            if (activity != null) {

                if (editDto.getTrainerId() != null){
                    activity.setTrainerId(editDto.getTrainerId());
                }
                if (editDto.getObjectId() != null){
                    activity.setObjectId(editDto.getObjectId());
                }
                if (editDto.getStartTime() != null){
                    activity.setStartTime(editDto.getStartTime());
                }
                if (editDto.getMaxParticipants() > 0){
                    activity.setMaxParticipants(editDto.getMaxParticipants());
                }
                if (editDto.getDuration() > 0){
                    activity.setDuration(editDto.getDuration());
                }
                if (editDto.getActivityType() != null){
                    activity.setActivityType(editDto.getActivityType());
                }
                if (editDto.getPrice() > 0){
                    activity.setPrice(editDto.getPrice());
                }
                if (editDto.getDescription() != null){
                    activity.setDescription(editDto.getDescription());
                }

                activity = activityRepository.save(activity);

                return Optional.of(activity);
            }
        }
        return Optional.empty();
    }

//    public Optional<Activity> getActivity(Long id){
//        if (activityRepository.findById(id).isPresent()){
//            TodoTask todoTask = todoTaskRepository.findById(id).get();
//
//            return Optional.of(todoTask);
//        }
//        return Optional.empty();
//    }

    // , get, list, remove
}
