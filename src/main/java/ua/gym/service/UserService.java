package ua.gym.service;

import ua.gym.entity.User;

import java.util.Optional;

public interface UserService<T extends User> {

    void save(T user);

    Optional<T> getById(Long id);

    void updateBuId(Long id, T user);

    void deleteById(Long id);

}
