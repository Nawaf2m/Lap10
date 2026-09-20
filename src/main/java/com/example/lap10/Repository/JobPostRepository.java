package com.example.lap10.Repository;

import com.example.lap10.Model.JobPost;
import com.example.lap10.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {
    JobPost findJobPostById(Integer id);
}
