package ua.gym.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.gym.entity.Trainee;
import ua.gym.entity.Trainer;
import ua.gym.entity.Training;
import ua.gym.service.TrainingService;
import ua.gym.service.UserService;

@Component
public class GymFacade {
    private final UserService<Trainee> traineeService;
    private final UserService<Trainer> trainerService;
    private final TrainingService trainingService;

    @Autowired
    public GymFacade(UserService<Trainee> traineeService,
                     UserService<Trainer> trainerService,
                     TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void saveTrainee(Trainee trainee) {
        traineeService.save(trainee);
    }

    public void saveTrainer(Trainer trainer) {
        trainerService.save(trainer);
    }

    public void saveTraining(Training training) {
        trainingService.createTraining(training);
    }

    public Trainee getTraineeById(Long id) {
        return traineeService.getById(id).get();
    }

    public Trainer getTrainerById(Long id) {
        return trainerService.getById(id).get();
    }

    public Training getTrainingById(Long id) {
        return trainingService.getTrainingById(id).get();
    }
}
