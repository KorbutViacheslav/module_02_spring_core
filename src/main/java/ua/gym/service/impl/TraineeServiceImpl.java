package ua.gym.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainee;
import ua.gym.service.UserService;

import java.util.Optional;

@Slf4j
@Service("traineeServiceImpl")
public class TraineeServiceImpl implements UserService<Trainee> {
    private final UserDao<Trainee> traineeUserDao;

    @Autowired
    public TraineeServiceImpl(UserDao<Trainee> userDao) {
        this.traineeUserDao = userDao;
    }

    @Override
    public void save(Trainee trainee) {
        if (trainee == null || trainee.getUserId() == null) {
            log.error("Failed to saved user: user id is null");
            throw new NullPointerException("Failed to saved user: user id is null");
        }
        trainee.generatePasswordAndUsername();
        traineeUserDao.save(trainee);
        log.info("Saved trainee with ID: {}", trainee.getUserId());
    }

    @Override
    public Optional<Trainee> getById(Long id) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            log.info("Get trainee with ID: {}", id);
        } else {
            log.warn("No trainee found with ID: {}", id);
        }
        return trainee;
    }

    @Override
    public void updateBuId(Long id, Trainee user) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            traineeUserDao.updateById(id, user);
            log.info("Updated trainee with ID: {}", id);
        } else {
            log.warn("Failed to update: No trainee found with ID: {}", id);
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Trainee> trainee = traineeUserDao.getById(id);
        if (trainee.isPresent()) {
            traineeUserDao.removeById(id);
            log.info("Remove trainee with ID: {}", id);
        } else {
            log.warn("Failed to remove: No trainee found with ID: {}", id);
        }
    }
}
