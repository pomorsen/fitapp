package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.repositories.ActivityRepository;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;



}
