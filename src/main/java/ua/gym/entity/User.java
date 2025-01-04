package ua.gym.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ua.gym.security.PasswordGenerator;
import ua.gym.security.UsernameGenerator;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    private Long userId;
    private String firstName, lastName, username, password;
    @JsonProperty("isActive")
    private boolean isActive = true;

    public User(Long userId, String firstName, String lastName, boolean isActive) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public void generatePasswordAndUsername() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        this.password = passwordGenerator.generatePassword();
        UsernameGenerator usernameGenerator = new UsernameGenerator();
        this.username = usernameGenerator.generateUsername(firstName, lastName);
    }
}
