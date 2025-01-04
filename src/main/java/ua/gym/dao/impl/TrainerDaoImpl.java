package ua.gym.dao.impl;

import ua.gym.dao.UserDao;
import ua.gym.entity.Trainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainerDaoImpl implements UserDao<Trainer> {
    private Map<String, Trainer> trainers = new HashMap<>();


    public TrainerDaoImpl(Map<String, Trainer> trainers) {
        this.trainers = trainers;
    }

    private void loadTrainers() {

    }

    @Override
    public void save(Trainer user) {
    }

    @Override
    public Optional<Trainer> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateById(Long id, Trainer user) {
    }

    @Override
    public void removeById(Long id) {
    }

    @Override
    public List<Trainer> getAll() {
        return List.of();
    }
}
