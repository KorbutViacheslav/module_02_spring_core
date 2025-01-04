package ua.gym.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainer;
import ua.gym.service.UserService;

import java.util.Optional;

@Slf4j
@Service("trainerServiceImpl")
public class TrainerServiceImpl implements UserService<Trainer> {
    private final UserDao<Trainer> trainerUserDao;

    @Autowired
    public TrainerServiceImpl(UserDao<Trainer> trainerUserDao) {
        this.trainerUserDao = trainerUserDao;
    }

    @Override
    public void save(Trainer trainer) {
        if (trainer == null || trainer.getUserId() == null) {
            log.error("Failed to saved trainer: id is null");
            throw new NullPointerException("Failed to saved trainer: id is null");
        }
        trainer.generatePasswordAndUsername();
        log.info("Saved trainer with ID: {}", trainer.getUserId());
        trainerUserDao.save(trainer);

    }

    @Override
    public Optional<Trainer> getById(Long id) {
        Optional<Trainer> trainer = trainerUserDao.getById(id);
        if (trainer.isPresent()) {
            log.info("Get trainer with ID: {}", id);
        } else {
            log.warn("No trainer found with ID: {}", id);
        }
        return trainer;
    }

    @Override
    public void updateBuId(Long id, Trainer user) {
        Optional<Trainer> trainer = trainerUserDao.getById(id);
        if (trainer.isPresent()) {
            trainerUserDao.updateById(id, user);
            log.info("Updated trainer with ID: {}", id);
        } else {
            log.warn("Failed to update: No trainer found with ID: {}", id);
        }

    }

    @Override
    public void deleteById(Long id) {
        Optional<Trainer> trainer = trainerUserDao.getById(id);
        if (trainer.isPresent()) {
            trainerUserDao.removeById(id);
            log.info("Remove trainer with ID: {}", id);
        } else {
            log.warn("Failed to remove: No trainer found with ID: {}", id);
        }
    }
}
