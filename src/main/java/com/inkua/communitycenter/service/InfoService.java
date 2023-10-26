package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.Information;

import java.util.List;

public interface InfoService {

    List<Information> findByType(String type);

    Information saveOrUpdate (Information information);

    Information delete (Long id);



}
