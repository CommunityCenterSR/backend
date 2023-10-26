package com.inkua.communitycenter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inkua.communitycenter.entity.Volunteer;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.VolunteerRepository;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private VolunteerRepository volunteerRepository;

    @Override
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer findById(Long id) {
        return null;
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer updateVolunteer(Long id, Volunteer volunteer) throws NotFoundException {
        
        Volunteer volunteerExist = volunteerRepository.findById(id).orElseThrow(
            ()-> new NotFoundException("No se encontro voluntario" + id));
        volunteer.setId(id);
        return volunteerRepository.save(volunteer);
    }

    @Override
    public void deleteVolunteer(Long id) {
        volunteerRepository.deleteById(id);
    }

}