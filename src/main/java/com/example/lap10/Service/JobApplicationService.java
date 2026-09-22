package com.example.lap10.Service;

import com.example.lap10.Model.JobApplication;
import com.example.lap10.Model.JobPost;
import com.example.lap10.Model.User;
import com.example.lap10.Repository.JobApplicationRepository;
import com.example.lap10.Repository.JobPostRepository;
import com.example.lap10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;

    public List<JobApplication> get(){
        return jobApplicationRepository.findAll();
    }

    public int applyForJob(JobApplication jobApplication){
        User user = userRepository.findUserById(jobApplication.getUserId());
        JobPost jobPost = jobPostRepository.findJobPostById(jobApplication.getJobPostId());

        if (user == null){
            return 0;
        }

        if (jobPost == null){
            return 1;
        }

        jobApplicationRepository.save(jobApplication);
        return 2;
    }

    public boolean update(Integer id, JobApplication jobApplication){
        JobApplication oldJobApplication = jobApplicationRepository.findJobApplicationById(id);

        if (oldJobApplication == null){
            return false;
        }

        oldJobApplication.setUserId(jobApplication.getUserId());
        oldJobApplication.setJobPostId(jobApplication.getJobPostId());

        jobApplicationRepository.save(oldJobApplication);
        return true;
    }

    public boolean withdrawJobApplication(Integer id){
        JobApplication jobApplication = jobApplicationRepository.findJobApplicationById(id);

        if (jobApplication == null){
            return false;
        }

        jobApplicationRepository.delete(jobApplication);
        return true;
    }
}
