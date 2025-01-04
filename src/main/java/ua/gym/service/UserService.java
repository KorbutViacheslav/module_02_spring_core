package ua.gym.service;

import ua.gym.entity.User;

import java.util.Optional;

public interface UserService<T extends User> {

    void saveUser(T user);

    Optional<T> getUser(Long id);

    void updateUser(Long id, T user);

    void deleteUser(Long id);

}
