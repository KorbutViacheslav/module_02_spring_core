package ua.gym.service;

import ua.gym.entity.Training;

import java.util.Optional;

public interface TrainingService {
    void createTraining(Training training);

    Optional<Training> getTrainingById(Long id);
}
