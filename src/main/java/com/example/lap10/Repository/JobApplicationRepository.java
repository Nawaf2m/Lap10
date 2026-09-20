package com.example.lap10.Repository;

import com.example.lap10.Model.JobApplication;
import com.example.lap10.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    JobApplication findJobApplicationById(Integer id);
}
