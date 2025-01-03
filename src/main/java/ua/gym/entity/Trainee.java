package ua.gym.entity;

import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Trainee extends User {
    private String address;
    private LocalDate birthday;

    public Trainee(Long userId, String firstName, String lastName, boolean isActive, String address, LocalDate birthday) {
        super(userId, firstName, lastName, isActive);
        this.address = address;
        this.birthday = birthday;
    }
}
