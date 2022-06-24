package com.example.springshop.process.domain.user.service;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.CreateUserDto;
import com.example.springshop.process.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createtUser(CreateUserDto insertUserDto) {
        User user = new User(insertUserDto.getUserName(), insertUserDto.getEmail(), insertUserDto.getPassword());
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는 유저입니다."));
        userRepository.delete(user);
    }
}
