package ua.gym.security;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class UsernameGenerator {
    private static final Set<String> USERNAMES = new HashSet<>();

    public String generateUsername(String firstName, String lastName) {
        String baseUsername = firstName + "." + lastName;

        int counter = 1;
        String uniqueUsername = baseUsername;
        while (USERNAMES.contains(uniqueUsername)) {
            log.info("Trainee userName not uniq. Added new userName: {}", uniqueUsername);
            uniqueUsername = baseUsername + counter;
            counter++;
        }
        USERNAMES.add(uniqueUsername);
        return uniqueUsername;
    }
}
