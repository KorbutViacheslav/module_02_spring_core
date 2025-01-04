package ua.gym.dao;

import ua.gym.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao<T extends User> {
    void save(T user);

    Optional<T> getById(Long id);

    void updateById(Long id, T user);

    void removeById(Long id);

    List<T> getAll();
}
