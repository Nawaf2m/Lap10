package com.example.lap10.Controller;

import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.JobApplication;
import com.example.lap10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobapplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        List<JobApplication> jobApplications = jobApplicationService.get();

        if (jobApplications.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("The list is empty"));
        }

        return ResponseEntity.status(200).body(jobApplications);
    }

    @PostMapping("/apply-for-job")
    public ResponseEntity<?> applyForJob(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        int status = jobApplicationService.applyForJob(jobApplication);
        if (status == 0){
            return ResponseEntity.status(200).body(new ApiResponse("User not found"));
        } else if (status == 1) {
            return ResponseEntity.status(200).body(new ApiResponse("Job post not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Job application added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid JobApplication jobApplication, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (jobApplicationService.update(id,jobApplication)){
            return ResponseEntity.status(200).body(new ApiResponse("Job application updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Job application not found"));
    }

    @DeleteMapping("/withdraw-job-application/{id}")
    public ResponseEntity<?> withdrawJobApplication(@PathVariable Integer id){
        if (jobApplicationService.withdrawJobApplication(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Job application deleted successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("Job application not found"));
    }
}
