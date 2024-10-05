package ru.otus.timofeev.task3.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.otus.timofeev.task3.exception.ConflictException;
import ru.otus.timofeev.task3.exception.NotFoundException;
import ru.otus.timofeev.task3.model.User;
import ru.otus.timofeev.task3.repository.UserRepository;

import java.util.List;

import static ru.otus.timofeev.task3.config.CacheConfig.CACHE_NAME;


@Service
@CacheConfig(cacheNames = CACHE_NAME)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final MessageSource messageSource;

    @CachePut(key = "#user.getId()")
    @Override
    public User create(User user) {
        log.info("Insert to database. User: {}", user);

        User savedUser;
        try {
            savedUser = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(
                    messageSource.getMessage("user.email.duplicate", null, null),
                    String.format("Email=%s", user.getEmail()));
        }

        log.info("User with ID {} is saved", savedUser.getId());

        return savedUser;
    }

    @Cacheable(key = "#id")
    @Override
    public User getById(Long id) {
        log.info("Select from database user with id {}", id);

        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with ID %d not found", id), ""));

        log.info("Found user: {}", user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = repository.findAll();
        log.info("Select from database {} users", users.size());
        return users;
    }
}
