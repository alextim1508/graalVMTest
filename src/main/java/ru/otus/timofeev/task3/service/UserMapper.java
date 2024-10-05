package ru.otus.timofeev.task3.service;

import com.google.common.hash.Hashing;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.timofeev.task3.dto.NewUserDto;
import ru.otus.timofeev.task3.dto.UserDto;
import ru.otus.timofeev.task3.model.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class UserMapper {

    public abstract List<UserDto> toDto(List<User> users);

    public abstract UserDto toDto(User users);

    @Mapping(target = "hashPass", expression = "java(hash(userDto.getPassword()))")
    public abstract User toEntity(NewUserDto userDto);

    @SneakyThrows
    public long hash(String pass) {
        return Hashing.sha512().hashString(pass, StandardCharsets.UTF_8).asLong();
    }
}
