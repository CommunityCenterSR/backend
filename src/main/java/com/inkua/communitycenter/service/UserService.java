package com.inkua.user.service;

import com.inkua.user.model.User;
import com.inkua.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    public List<User> findAllUsers() {
        return iUserRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

    public User updateById(User request, Long id) {
        User user = iUserRepository.findById(id).get();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        return user;
    }

    public Boolean deleteUser(Long id) {
        try {
            iUserRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
