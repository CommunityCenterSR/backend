package com.example.demo.controller;

import com.example.demo.entity.Information;
import com.example.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/information")
public class InfoController {
    @Autowired
    InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping("/{infoId}")
    public List<Information> findByType(@PathVariable("infoId") String type){
        return infoService.findByType(type);
    }

    @PostMapping
    public void createOrUpdate(@RequestBody Information information){
        infoService.saveOrUpdate(information);
    }

    @DeleteMapping("/{infoId}")
    public void deleteInfo(@PathVariable("infoId") Long infoId){
        infoService.delete(infoId);
    }
}
