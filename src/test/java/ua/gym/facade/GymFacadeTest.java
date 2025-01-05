package ua.gym.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.gym.config.SpringConfig;
import ua.gym.entity.Trainee;
import ua.gym.entity.Trainer;
import ua.gym.entity.Training;
import ua.gym.entity.TrainingType;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class GymFacadeTest {

    @Autowired
    private GymFacade gymFacade;

    @Test
    void saveTrainee() {
        Trainee trainee = new Trainee(19L,
                "FirstName",
                "LastName",
                true,
                "Address",
                LocalDate.of(1991, 8, 30));
        gymFacade.saveTrainee(trainee);
        Trainee retrieved = gymFacade.getTraineeById(trainee.getUserId());

        assertNotNull(retrieved, "Retrieved Trainee not to be null");
        assertEquals(trainee.getUsername(), retrieved.getUsername(), "Username should match");
    }

    @Test
    void saveTrainer() {
        Trainer trainer = new Trainer(6L,
                "John",
                "Snow",
                true,
                TrainingType.FITNESS);
        gymFacade.saveTrainer(trainer);
        Trainer retrieved = gymFacade.getTrainerById(trainer.getUserId());
        assertNotNull(retrieved, "Retrieved Trainer not to be null");
        assertEquals(trainer.getUsername(), retrieved.getUsername(), "Username should match");
    }

    @Test
    void saveTraining() {
        Training training = new Training(5L,
                5L,
                5L,
                "Zumba session",
                TrainingType.ZUMBA,
                LocalDate.of(2025, 1, 2),
                45);
        gymFacade.saveTraining(training);
        Training retrieved = gymFacade.getTrainingById(5L);
        assertNotNull(retrieved, "Retrieved Training not to be null");
        assertEquals(training.getTrainingId(), retrieved.getTrainingId(), "Retrieved Training not to be the same");
    }

    @Test
    void getTraineeById() {
    }

    @Test
    void getTrainerById() {
    }

    @Test
    void getTrainingById() {
    }
}