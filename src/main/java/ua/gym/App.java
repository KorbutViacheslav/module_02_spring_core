package ua.gym;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.gym.config.SpringConfig;
import ua.gym.dao.impl.TraineeDaoImpl;
import ua.gym.entity.Trainee;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TraineeDaoImpl dao = context.getBean(TraineeDaoImpl.class);
        System.out.println(dao.getUser(3L));
        Trainee trainee = new Trainee(19L,"FirstName","LastName",
                true,"Addres", LocalDate.of(1991,8,30));
        Trainee trainee1 = new Trainee(20L,"FirstName","LastName",
                true,"Addres1", LocalDate.of(1990,10,30));
        Trainee trainee2 = new Trainee(21L,"FirstName","LastName",
                true,"Addres2", LocalDate.of(1995,11,30));
        dao.saveUser(trainee);
        dao.saveUser(trainee1);
        dao.saveUser(trainee2);
        System.out.println(dao.getUser(20L));
        System.out.println(dao.getUser(21L));
    }
}