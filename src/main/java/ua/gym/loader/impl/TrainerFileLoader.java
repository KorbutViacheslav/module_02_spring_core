package ua.gym.loader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.gym.entity.Trainer;
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
public class TrainerFileLoader implements FileLoader<Trainer> {
    private final ObjectMapper mapper;
    private final Path path;

    public TrainerFileLoader(@Value("${trainers.storage}") String path) {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.path = Paths.get(path);

    }

    @Override
    public Map<Long, Trainer> load() {
        Map<Long, Trainer> trainers = new HashMap<>();
        try (var br = Files.newBufferedReader(path)) {
            List<Trainer> trainersList = mapper.readValue(br, new TypeReference<>() {
            });
            trainersList.forEach(Trainer::generatePasswordAndUsername);
            for (Trainer t : trainersList) {
                trainers.put(t.getUserId(), t);
            }
            log.info("Successfully loaded {} trainers from file: {}", trainers.size(), path);
        } catch (IOException e) {
            log.error("Failed to load trainers from file: {}", path, e);
            throw new RuntimeException("Failed to load trainers from file: " + path, e);
        }
        return trainers;
    }
}
