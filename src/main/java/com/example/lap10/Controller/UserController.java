package com.example.lap10.Controller;

import com.example.lap10.ApiResponse.ApiResponse;
import com.example.lap10.Model.User;
import com.example.lap10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        List<User> users = userService.get();

        if (users.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("The list is empty"));
        }

        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        userService.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if (userService.update(id,user)){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        if (userService.delete(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }
}
