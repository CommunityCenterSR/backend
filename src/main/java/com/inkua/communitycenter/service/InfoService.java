package com.example.demo.service;

import com.example.demo.entity.Information;
import com.example.demo.repository.IInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    @Autowired
    IInfoRepository iInfoRepository;

    public List<Information> findByType(String type){
        return iInfoRepository.findByType(type);
    }
    public void saveOrUpdate (Information information){
        iInfoRepository.save(information);
    }
    public void delete (Long id){
        iInfoRepository.deleteById(id);
    }

}
