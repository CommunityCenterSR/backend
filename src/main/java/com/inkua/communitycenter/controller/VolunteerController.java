package com.inkua.communitycenter.controller;
import java.util.List;

import com.inkua.communitycenter.entity.Volunteer;
import com.inkua.communitycenter.service.IVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/volunteers")
public class VolunteerController {

    @Autowired
    private IVolunteerService volunteerService;


    @GetMapping
    public ResponseEntity<List<Volunteer>> findAll() {
        return new ResponseEntity<>(volunteerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> findById(@PathVariable Long id) {
        return new ResponseEntity<>((volunteerService).findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        return new ResponseEntity<>(volunteerService.createVolunteer(volunteer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteer) {
        return new ResponseEntity<>(this.volunteerService.updateVolunteer(id, volunteer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable Long id) {
        return new ResponseEntity<>(volunteerService.deleteVolunteer(id), HttpStatus.OK);
    }
}
