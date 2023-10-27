package com.inkua.communitycenter.service;
import java.util.List;

import com.inkua.communitycenter.entity.Volunteer;

public interface VolunteerService {

    List<Volunteer> findAll();

    Volunteer findById(Long id);

    Volunteer createVolunteer(Volunteer volunteer);

    Volunteer updateVolunteer(Long id, Volunteer volunteer);
    
    void deleteVolunteer(Long id);
}
