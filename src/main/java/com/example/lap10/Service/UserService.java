package com.example.lap10.Service;

import com.example.lap10.Model.User;
import com.example.lap10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> get(){
        return userRepository.findAll();
    }

    public void add(User user){
        userRepository.save(user);
    }

    public boolean update(Integer id, User user){
        User oldUser = userRepository.findUserById(id);

        if (oldUser == null){
            return false;
        }

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());
        userRepository.save(oldUser);
        return true;
    }

    public boolean delete(Integer id){
        User user = userRepository.findUserById(id);

        if (user == null){
            return false;
        }

        userRepository.delete(user);
        return true;
    }
}
