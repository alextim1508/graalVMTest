package ru.otus.timofeev.task3.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task3.dto.NewUserDto;
import ru.otus.timofeev.task3.dto.UserDto;
import ru.otus.timofeev.task3.model.User;
import ru.otus.timofeev.task3.service.UserMapper;
import ru.otus.timofeev.task3.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody NewUserDto userDto) {
        log.info("Creation request. UserDto : {}", userDto);
        User user = mapper.toEntity(userDto);

        User savedUser = service.create(user);

        return mapper.toDto(savedUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long id) {
        log.info("Getting user with ID {} request", id);
        User user = service.getById(id);
        return mapper.toDto(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        log.info("Getting all users request");
        List<User> users = service.getUsers();
        return mapper.toDto(users);
    }
}


