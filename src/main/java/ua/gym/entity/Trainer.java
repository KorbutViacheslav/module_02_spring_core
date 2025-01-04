package ua.gym.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Trainer extends User {
    private TrainingType specialization;


    public Trainer(Long userId, String firstName, String lastName, boolean isActive, TrainingType specialization) {
        super(userId, firstName, lastName, isActive);
        this.specialization = specialization;
    }
}
