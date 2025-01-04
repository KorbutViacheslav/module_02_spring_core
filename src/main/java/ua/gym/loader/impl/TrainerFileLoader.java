package ua.gym.loader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class TrainerFileLoader implements FileLoader {
    private final ObjectMapper mapper;

    @Value("${trainers.storage}")
    private String path;

    public TrainerFileLoader() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

    }

    @Override
    public Map<Long, Trainer> load() {
        Map<Long, Trainer> trainers = new HashMap<>();
        Path filePath = Paths.get(path);
        try (var br = Files.newBufferedReader(filePath)) {
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
