package ua.gym.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
        this.password = "random";
        this.username = getFirstName() + "." + getLastName();
    }
}
