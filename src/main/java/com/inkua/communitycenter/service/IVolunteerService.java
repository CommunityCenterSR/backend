package com.inkua.communitycenter.service;
import java.util.List;

import com.inkua.communitycenter.entity.Volunteer;

public interface IVolunteerService {

    List<Volunteer> findAll();

    Volunteer findById(Long id);

    Volunteer createVolunteer(Volunteer volunteer);

    Volunteer updateVolunteer(Long id, Volunteer volunteer);
    
    Volunteer deleteVolunteer(Long id);
}
