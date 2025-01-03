package ua.gym.dao.impl;

import ua.gym.dao.UserDao;
import ua.gym.entity.Trainer;
import ua.gym.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TrainerDaoImpl implements UserDao<Trainer> {
    private Map<String, Trainer> trainers = new HashMap<>();



    @Override
    public boolean saveUser(Trainer user) {
        return false;
    }

    @Override
    public Optional<Trainer> getUser(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean updateUser(Long id, User user) {
        return false;
    }

    @Override
    public boolean removeUser(Long id) {
        return false;
    }
}
