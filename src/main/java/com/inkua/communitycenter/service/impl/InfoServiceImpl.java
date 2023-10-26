package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.Information;
import com.inkua.communitycenter.exception.NotFoundException;
import com.inkua.communitycenter.repository.IInfoRepository;
import com.inkua.communitycenter.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private IInfoRepository infoRepository;

    @Override
    public List<Information> findByType(String type){
        return infoRepository.findByType(type);
    }

    @Override
    public Information saveOrUpdate (Information information){
        return infoRepository.save(information);
    }

    @Override
    public Information delete (Long id){
        Optional<Information> info = infoRepository.findById(id);

        if (info.isEmpty())
            throw new NotFoundException("No existe Información con ID: " + id);

        infoRepository.deleteById(id);
        return info.get();

    }

}
