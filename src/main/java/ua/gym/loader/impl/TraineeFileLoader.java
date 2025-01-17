package ua.gym.loader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.gym.entity.Trainee;
import ua.gym.loader.FileLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class TraineeFileLoader implements FileLoader<Trainee> {
    private final ObjectMapper mapper;
    private final Path path;

    public TraineeFileLoader(@Value("${trainees.storage}") String path) {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.path = Paths.get(path);
    }

    @Override
    public Map<Long, Trainee> load() {
        Map<Long, Trainee> users = new HashMap<>();
        try (var br = Files.newBufferedReader(path)) {
            List<Trainee> trainees = mapper.readValue(br, new TypeReference<>() {
            });
            trainees.forEach(Trainee::generatePasswordAndUsername);
            for (Trainee trainee : trainees) {
                users.put(trainee.getUserId(), trainee);
            }
            log.info("Successfully loaded {} trainees from file: {}", users.size(), path);
        } catch (IOException e) {
            log.error("Failed to load trainees from file: {}", path, e);
            throw new RuntimeException("Failed to load trainees from file: " + path, e);
        }
        return users;
    }
}
