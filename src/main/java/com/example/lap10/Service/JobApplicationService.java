package com.example.lap10.Service;

import com.example.lap10.Model.JobApplication;
import com.example.lap10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> get(){
        return jobApplicationRepository.findAll();
    }

    public void applyForJob(JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
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
