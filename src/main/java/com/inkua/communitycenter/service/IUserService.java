package com.inkua.communitycenter.service;

import com.inkua.communitycenter.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAllUsers();
    Optional<User> findByEmail(String email);
    User saveUser(User user);
    User updateById(User request, Long id);
    User deleteUser(Long id);

}
