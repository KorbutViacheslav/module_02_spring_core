package ua.gym.dao;

import ua.gym.entity.User;

import java.util.Optional;

public interface UserDao<T extends User> {
    boolean saveUser(T user);

    Optional<T> getUser(Long id);

    boolean updateUser(Long id, User user);

    boolean removeUser(Long id);
}
