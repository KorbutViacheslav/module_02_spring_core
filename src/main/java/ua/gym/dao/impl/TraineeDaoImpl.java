package ua.gym.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.gym.dao.UserDao;
import ua.gym.entity.Trainee;
import ua.gym.entity.User;
import ua.gym.loader.FileLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class TraineeDaoImpl implements UserDao<Trainee> {
    private final Map<Long, Trainee> trainees = new HashMap<>();
    private final FileLoader<Trainee> fileLoader;

    @Autowired
    public TraineeDaoImpl(FileLoader<Trainee> fileLoader) {
        this.fileLoader = fileLoader;
        loadTrainees();
    }

    private void loadTrainees() {
        trainees.putAll(fileLoader.loadUsers());
    }

    @Override
    public boolean saveUser(Trainee trainee) {
        if (trainee == null || trainee.getUserId() == null) {
            log.error("Failed to saved user: user id is null");
            return false;
        }
        trainee.generatePasswordAndUsername();
        String baseUsername = trainee.getUsername();
        int count = 1;
        while (isUniqUserName(trainee.getUsername())) {
            trainee.setUsername(baseUsername + count);
            count++;
            log.info("Trainee userName not uniq. Added new userName: {}", trainee.getUsername());
        }
        log.info("Saved trainee with ID: {}", trainee.getUserId());
        return trainees.put(trainee.getUserId(), trainee) == null;
    }

    @Override
    public Optional<Trainee> getUser(Long id) {
        Trainee trainee = trainees.get(id);
        if (trainee != null) {
            log.info("Get trainee with ID: {}", id);
        } else {
            log.warn("No trainee found with ID: {}", id);
        }
        return Optional.ofNullable(trainee);
    }

    @Override
    public boolean updateUser(Long id, User user) {
        Optional<Trainee> trainee = getUser(id);
        if (trainee.isPresent()) {
            trainees.put(id, trainee.get());
            log.info("Updated trainee with ID: {}", id);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUser(Long id) {
        Trainee trainee = trainees.get(id);
        if (trainee != null) {
            trainees.remove(id);
            log.info("Remove trainee with ID: {}", id);
            return true;
        } else {
            log.warn("Failed to remove: No trainee found with ID: {}", id);
            return false;
        }
    }

    private boolean isUniqUserName(String username) {
        return trainees.values()
                .stream()
                .anyMatch(trainee -> trainee.getUsername().equals(username));
    }
}
