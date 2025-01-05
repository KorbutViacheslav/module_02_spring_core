package ua.gym.dao;

import ua.gym.entity.Training;

public interface TrainingDao {
    void create(Training training);

    Training select(Long id);
}
