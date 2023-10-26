










/*
* Está comentado hasta que terminen el service y la entidad
* */





//package com.inkua.communitycenter.controller;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/volunteers")
//public class VolunteerController {
//
//    @Autowired
//    private VolunteerService volunteerService;
//
//
//    @GetMapping("/")
//    public List<Volunteer> findAll() {
//        return volunteerService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Volunteer findById(@PathVariable Long id) {
//        return (volunteerService).findById(id);
//    }
//
//    @PostMapping("/")
//    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
//        return volunteerService.createVolunteer(volunteer);
//    }
//
//    @PutMapping("/{id}")
//    public Volunteer updateVolunteer(@PathVariable Long id, @RequestBody Volunteer volunteer) {
//        return this.volunteerService.updateVolunteer(id, volunteer);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteVolunteer(@PathVariable Long id) {
//        volunteerService.deleteVolunteer(id);
//    }
//}