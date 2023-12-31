package com.inkua.communitycenter.controller;

import com.inkua.communitycenter.entity.Information;
import com.inkua.communitycenter.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/information")
public class InfoController {

    @Autowired
    private InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping
    public ResponseEntity<List<Information>> findAll(){
        return new ResponseEntity<>(infoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/type/{infoId}")
    public ResponseEntity<Information> findByType(@PathVariable("infoId") String type){
        return new ResponseEntity<>(infoService.findByType(type).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Information> createOrUpdate(@RequestBody Information information){
        return new ResponseEntity<>(infoService.saveOrUpdate(information), HttpStatus.CREATED);
    }

    @DeleteMapping("/{infoId}")
    public ResponseEntity<Information> deleteInfo(@PathVariable("infoId") Long infoId){
        return new ResponseEntity<>(infoService.delete(infoId), HttpStatus.OK);
    }
}
