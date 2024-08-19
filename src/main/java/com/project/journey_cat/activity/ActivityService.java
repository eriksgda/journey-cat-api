package com.project.journey_cat.activity;

import com.project.journey_cat.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public ActivityCreateResponse registerActivity(ActivityRequest request, Trip trip){
        Activity newActivity = new Activity(request.title(), request.occurs_at(), trip);

        this.repository.save(newActivity);

        return new ActivityCreateResponse(newActivity.getId());
    }

    public List<ActivityData> getAllActivities(UUID trip_id){
        return this.repository.findByTripId(trip_id).stream().map(activities -> new ActivityData(activities.getId(), activities.getTitle(), activities.getOccursAt())).toList();
    }
}
