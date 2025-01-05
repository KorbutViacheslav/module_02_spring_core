package ua.gym.loader.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.gym.entity.Training;
import ua.gym.loader.FileLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TrainingFileLoader implements FileLoader<Training> {
    private final ObjectMapper mapper;
    private final Path path;

    public TrainingFileLoader(@Value("${trainings.storage}") String path) {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        this.path = Paths.get(path);
    }

    @Override
    public Map<Long, Training> load() {
        Map<Long, Training> trainings = new HashMap<>();
        try (var br = Files.newBufferedReader(path)) {
            List<Training> trainingsList = mapper.readValue(br, new TypeReference<>() {
            });
            for (Training t : trainingsList) {
                trainings.put(t.getTrainingId(), t);
            }
            log.info("Successfully loaded {} trainings from file: {}", trainings.size(), path);
        } catch (IOException e) {
            log.error("Failed to load trainings from file: {}", path, e);
            throw new RuntimeException("Failed to load trainings from file: " + path, e);
        }
        return trainings;
    }
}
