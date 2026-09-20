package com.example.lap10.Service;

import com.example.lap10.Model.JobPost;
import com.example.lap10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> get(){
        return jobPostRepository.findAll();
    }

    public void add(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public boolean update(Integer id, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.findJobPostById(id);

        if (oldJobPost == null){
            return false;
        }

        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setPostingDate(jobPost.getPostingDate());

        jobPostRepository.save(oldJobPost);
        return true;
    }

    public boolean delete(Integer id){
        JobPost jobPost = jobPostRepository.findJobPostById(id);

        if (jobPost == null){
            return false;
        }

        jobPostRepository.delete(jobPost);
        return true;
    }
}
