package ua.gym.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainee;
import ua.gym.loader.FileLoader;

import java.util.*;

@Slf4j
@Repository
public class TraineeDaoImpl implements UserDao<Trainee> {
    private final Map<Long, Trainee> trainees = new HashMap<>();
    private final FileLoader<Trainee> fileLoader;

    @Autowired
    public TraineeDaoImpl(FileLoader<Trainee> fileLoader) {
        this.fileLoader = fileLoader;
        loadTrainees();
        log.info("Trainees from file loaded");
    }

    private void loadTrainees() {
        trainees.putAll(fileLoader.load());
    }

    @Override
    public void save(Trainee trainee) {
        trainees.put(trainee.getUserId(), trainee);
    }

    @Override
    public Optional<Trainee> getById(Long id) {
        return Optional.ofNullable(trainees.get(id));
    }

    @Override
    public void updateById(Long id, Trainee trainee) {
        trainees.put(id, trainee);
    }

    @Override
    public void removeById(Long id) {
        trainees.remove(id);
    }

    @Override
    public List<Trainee> getAll() {
        return new ArrayList<>(this.trainees.values());
    }

}


