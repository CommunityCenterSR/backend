package com.inkua.communitycenter.service.impl;

import java.util.List;

import com.inkua.communitycenter.repository.IVolunteerRepository;
import com.inkua.communitycenter.service.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inkua.communitycenter.entity.Volunteer;
import com.inkua.communitycenter.exception.NotFoundException;

@Service
public class VolunteerServiceImpl implements IVolunteerService {

    @Autowired
    private IVolunteerRepository volunteerRepository;

    @Override
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer findById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(()->
                new NotFoundException("No se encontró voluntario con ID: " + id));
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer updateVolunteer(Long id, Volunteer volunteer) throws NotFoundException {
        Volunteer volunteerExist = volunteerRepository.findById(id).orElseThrow(
            ()-> new NotFoundException("No se encontró voluntario" + id));
        volunteer.setId(id);
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer deleteVolunteer(Long id) {
        Volunteer volunteerExist = volunteerRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("No se encontró voluntario" + id));

        volunteerRepository.deleteById(id);

        return volunteerExist;
    }

}