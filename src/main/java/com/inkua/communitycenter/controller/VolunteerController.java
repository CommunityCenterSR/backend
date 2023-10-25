package com.inkua.communitycenter.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inkua.communitycenter.entity.Volunteer;
import com.inkua.communitycenter.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;


    @GetMapping("/")
    public List<Volunteer> findAll() {
        return volunteerService.findAll();
    }

    @GetMapping("/{id}")
    public Volunteer findById(@PathVariable Long id) {
        return (volunteerService).findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer newVolunteer = volunteerService.saveVolunteer(volunteer);
        return new ResponseEntity<>(newVolunteer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Volunteer updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteer) {
        return this.volunteerService.updateVolunteer(id, volunteer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable Long id) {
        Volunteer volunteerDeleted = volunteerService.deleteVolunteer(id);
        return new ResponseEntity<>(volunteerDeleted, HttpStatus.OK);
    }
}
