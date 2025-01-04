package ua.gym.service.impl;

import org.springframework.stereotype.Service;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainee;
import ua.gym.service.UserService;

import java.util.Optional;

@Service
public class TraineeServiceImpl implements UserService<Trainee> {
    private final UserDao<Trainee> userDao;

    public TraineeServiceImpl(UserDao<Trainee> userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(Trainee user) {

    }

    @Override
    public Optional<Trainee> getUser(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateUser(Trainee user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
