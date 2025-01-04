package ua.gym;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.gym.config.SpringConfig;
import ua.gym.entity.Trainee;
import ua.gym.entity.Trainer;
import ua.gym.entity.TrainingType;
import ua.gym.service.UserService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Trainee trainee = new Trainee(19L,
                "FirstName",
                "LastName",
                true,
                "Address",
                LocalDate.of(1991, 8, 30));
        Trainee trainee1 = new Trainee(20L,
                "FirstName",
                "LastName",
                true,
                "Address1",
                LocalDate.of(1990, 10, 30));
        Trainee trainee2 = new Trainee(21L,
                "FirstName",
                "LastName",
                true,
                "Address2",
                LocalDate.of(1995, 11, 30));


        UserService<Trainee> traineeService = context.getBean("traineeServiceImpl", UserService.class);
        System.out.println(traineeService.getById(1L));
        System.out.println(traineeService.getById(2L));
        System.out.println(traineeService.getById(3L));

        traineeService.save(trainee);
        traineeService.save(trainee1);
        traineeService.save(trainee2);
        System.out.println(traineeService.getById(21L));

        Trainer trainer = new Trainer(6L, "John", "Snow", true, TrainingType.FITNESS);

        UserService<Trainer> trainerService = context.getBean("trainerServiceImpl", UserService.class);
        trainerService.save(trainer);

        System.out.println(trainerService.getById(1L));
        System.out.println(trainerService.getById(6L));


    }
}