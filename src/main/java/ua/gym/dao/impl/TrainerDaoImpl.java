package ua.gym.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainer;
import ua.gym.loader.FileLoader;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Repository
public class TrainerDaoImpl implements UserDao<Trainer> {
    private final Map<Long, Trainer> trainers = new HashMap<>();
    private final FileLoader<Trainer> fileLoader;

    @Autowired
    public TrainerDaoImpl(FileLoader<Trainer> fileLoader) {
        this.fileLoader = fileLoader;
    }

    @PostConstruct
    private void loadTrainers() {
        trainers.putAll(fileLoader.load());
        log.info("Load trainers from file");
    }

    @Override
    public void save(Trainer trainer) {
        trainers.put(trainer.getUserId(), trainer);
    }

    @Override
    public Optional<Trainer> getById(Long id) {
        return Optional.ofNullable(trainers.get(id));
    }

    @Override
    public void updateById(Long id, Trainer user) {
        trainers.put(id, user);
    }

    @Override
    public void removeById(Long id) {
        trainers.remove(id);
    }

    @Override
    public List<Trainer> getAll() {
        return new ArrayList<>(trainers.values());
    }
}
