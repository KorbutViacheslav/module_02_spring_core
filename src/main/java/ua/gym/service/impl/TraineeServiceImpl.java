package ua.gym.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainee;
import ua.gym.service.UserService;

import java.util.Optional;

@Slf4j
@Service
public class TraineeServiceImpl implements UserService<Trainee> {
    private final UserDao<Trainee> traineeUserDao;

    @Autowired
    public TraineeServiceImpl(UserDao<Trainee> userDao) {
        this.traineeUserDao = userDao;
    }

    @Override
    public void saveUser(Trainee trainee) {
        if (trainee == null || trainee.getUserId() == null) {
            log.error("Failed to saved user: user id is null");
            throw new NullPointerException("Failed to saved user: user id is null");
        }
        trainee.generatePasswordAndUsername();
        log.info("Saved trainee with ID: {}", trainee.getUserId());
        traineeUserDao.save(trainee);
    }

    @Override
    public Optional<Trainee> getUser(Long id) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            log.info("Get trainee with ID: {}", id);
        } else {
            log.warn("No trainee found with ID: {}", id);
        }
        return trainee;
    }

    @Override
    public void updateUser(Long id, Trainee user) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            traineeUserDao.updateById(id, trainee.get());
            log.info("Updated trainee with ID: {}", id);
        } else {
            log.warn("Failed to update: No trainee found with ID: {}", id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            traineeUserDao.removeById(id);
            log.info("Remove trainee with ID: {}", id);
        } else {
            log.warn("Failed to remove: No trainee found with ID: {}", id);
        }
    }

    private boolean isUniqUserName(String username) {
        return traineeUserDao.getAll()
                .stream()
                .anyMatch(trainee -> trainee.getUsername().equals(username));
    }
}
