package ua.gym;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.gym.config.SpringConfig;
import ua.gym.entity.Trainee;
import ua.gym.service.UserService;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);

        Trainee trainee = new Trainee(19L,
                "FirstName",
                "LastName",
                true,
                "Addres",
                LocalDate.of(1991, 8, 30));
        Trainee trainee1 = new Trainee(20L,
                "FirstName",
                "LastName",
                true,
                "Addres1",
                LocalDate.of(1990, 10, 30));
        Trainee trainee2 = new Trainee(21L,
                "FirstName",
                "LastName",
                true,
                "Addres2",
                LocalDate.of(1995, 11, 30));


        UserService<Trainee> userService = context.getBean(UserService.class);
        System.out.println(userService.getUser(1L));
        System.out.println(userService.getUser(2L));
        System.out.println(userService.getUser(3L));

        userService.saveUser(trainee);
        userService.saveUser(trainee1);
        userService.saveUser(trainee2);
        System.out.println(userService.getUser(21L));

    }
}