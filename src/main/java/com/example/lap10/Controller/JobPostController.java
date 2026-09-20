package com.example.lap10.Controller;

import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.JobPost;
import com.example.lap10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        List<JobPost> jobPosts = jobPostService.get();

        if (jobPosts.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("The list is empty"));
        }

        return ResponseEntity.status(200).body(jobPosts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        jobPostService.add(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("Job post added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (jobPostService.update(id,jobPost)){
            return ResponseEntity.status(200).body(new ApiResponse("Job post updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if (jobPostService.delete(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Job post deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Job post not found"));
    }
}
