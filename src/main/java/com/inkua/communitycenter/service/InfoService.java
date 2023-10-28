package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.Information;

import java.util.List;
import java.util.Optional;

public interface InfoService {

    List<Information> findAll();
    Optional<Information> findByType(String type);

    Information saveOrUpdate (Information information);

    Information delete (Long id);



}
