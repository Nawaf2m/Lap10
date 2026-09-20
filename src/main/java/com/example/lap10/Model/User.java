package com.example.lap10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "Name can't be empty")
    @Length(min = 5, message = "Name must be more than 4 characters")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Wrong email format")
    private String email;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Password can't be empty")
    private String password;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "Age can't be empty")
    @Positive(message = "Age must be positive")
    @Min(value = 21, message = "Age must be more than 21")
    private Integer age;

//    @Column(nullable = false, length = 25, check = @CheckConstraint(constraint = "role IN ('Job_Seeker','Employer'"))
    @NotEmpty(message = "Role can't be empty")
    @Pattern(regexp = "^(Job_Seeker|Employer)$")
    private String role;
}
