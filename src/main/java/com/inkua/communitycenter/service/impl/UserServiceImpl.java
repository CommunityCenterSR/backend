package com.inkua.communitycenter.service.impl;

import com.inkua.communitycenter.entity.User;
import com.inkua.communitycenter.exception.NotFoundException;
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
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new NotFoundException("No se encontró usuario con email " + email);
        return user;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateById(User request, Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new NotFoundException("No se encontró usuario con id: " + id);
        }
        request.setId(id);

        return userRepository.save(request);
    }

    @Override
    public User deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new NotFoundException("No se encontró usuario con id: " + id);
        }
        userRepository.deleteById(id);
        return user.get();

    }
}
