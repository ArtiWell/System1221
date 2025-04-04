package com.example.system1221.service;

import com.example.system1221.dto.UserDto;
import com.example.system1221.dao.user.UserEntity;
import com.example.system1221.dao.user.UserRepository;
import com.example.system1221.exceptions.EmailAlreadyExistsException;
import com.example.system1221.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final CalorieCalculator calorieCalculator;

    public UserDto createUser(UserDto userDto){
        if (userRepository.existsByEmail(userDto.email())) {
            throw new EmailAlreadyExistsException(userDto.email());
        }
        UserEntity user = new UserEntity();
        user.setName(userDto.name());
        user.setEmail(userDto.email());
        user.setAge(userDto.age());
        user.setWeight(userDto.weight());
        user.setHeight(userDto.height());
        user.setGoal(userDto.goal());
        user.setDailyCalorieNorm(calorieCalculator.calculateDailyCalories(user));

        userRepository.save(user);

        return UserDto.fromEntity(user);
    }

    public UserDto getUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
        return UserDto.fromEntity(user);
    }

}
