package com.example.lap10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 5, message = "Title must be more than 4 characters")
    private String title;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Description can't be empty")
    private String description;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Location can't be empty")
    private String location;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Location can't be empty")
    @Positive(message = "Salary can't be negative or zero")
    private Integer salary;

    @Column(columnDefinition = "date not null")
    @NotNull(message = "Posting date is required")
    private LocalDate postingDate;
}
