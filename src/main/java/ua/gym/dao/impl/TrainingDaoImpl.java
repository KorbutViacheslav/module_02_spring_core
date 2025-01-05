package ua.gym.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.gym.dao.TrainingDao;
import ua.gym.entity.Training;
import ua.gym.loader.FileLoader;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Repository
public class TrainingDaoImpl implements TrainingDao {
    private Map<Long, Training> trainingStorage;

    private final FileLoader<Training> fileLoader;

    @Autowired
    public TrainingDaoImpl(FileLoader<Training> fileLoader) {
        this.fileLoader = fileLoader;

    }

    @PostConstruct
    private void loadTrainings() {
        trainingStorage = fileLoader.load();
        log.info("Loaded trainings from file");
    }

    @Override
    public void create(Training training) {
        trainingStorage.put(training.getTrainingId(), training);
    }

    @Override
    public Training select(Long id) {
        return trainingStorage.get(id);
    }
}
