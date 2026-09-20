package com.example.lap10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "userId can't be empty")
    private Integer userId;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "jobPostId can't be empty")
    private Integer jobPostId;
}
