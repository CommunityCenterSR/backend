package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.repository.IUserRepository;
import com.inkua.communitycenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateById(User request, Long id) {
        User user = userRepository.findById(id).get();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }

    @Override
    public Boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
