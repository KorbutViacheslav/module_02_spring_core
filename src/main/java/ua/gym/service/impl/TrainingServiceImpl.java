package ua.gym.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gym.dao.TrainingDao;
import ua.gym.entity.Training;
import ua.gym.service.TrainingService;

import java.util.Optional;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingDao trainingDao;

    @Autowired
    public TrainingServiceImpl(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    @Override
    public void createTraining(Training training) {
        if (training == null || training.getTrainingId() == null) {
            log.error("Failed to saved training: id is null");
            throw new NullPointerException("Failed to saved training: id is null");
        }
        trainingDao.create(training);
        log.info("Created training with id {}", training.getTrainingId());

    }

    @Override
    public Optional<Training> getTrainingById(Long id) {
        Optional<Training> training = Optional.ofNullable(trainingDao.select(id));
        if (training.isPresent()) {
            log.info("Get training with id {}", id);
        } else {
            log.warn("Training with id {} not found", id);
        }
        return training;
    }
}
