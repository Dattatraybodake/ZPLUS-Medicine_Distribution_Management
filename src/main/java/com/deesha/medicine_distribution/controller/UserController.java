package com.deesha.medicine_distribution.controller;

import com.deesha.medicine_distribution.dto.LoginRequest;
import com.deesha.medicine_distribution.dto.LoginResponse;
import com.deesha.medicine_distribution.model.UserModel;
import com.deesha.medicine_distribution.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity registerUser(@RequestBody UserModel usermodel)
    {
        boolean saved = userService.saveUsers(usermodel);
        {
            if(saved)
            {
                return new ResponseEntity<>(saved, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity(saved, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping
    public String testing()
    {
        return "Testing";
    }

    @GetMapping("/{test}")
    public String testing(@PathVariable("test") String test)
    {
        return test;
    }

    @GetMapping("/Viewuser")
    public List<UserModel> getAllUsers() throws Exception
    {
        List<UserModel> list = userService.viewAllUsers();
        if(list.size()!=0)
        {
            System.out.println("list ="+list.size());
            return userService.viewAllUsers();
        }
        else
        {
            throw new RuntimeException("There is No data In Database");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
    {
        LoginResponse loginresponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginresponse);
    }

    // Update user all details.
//    @PutMapping("/update")
//    public ResponseEntity<Boolean> updateUser( @RequestBody UserModel userModel) {
//        Boolean updated = userService.updateUserMaster(userModel);
//        if (Boolean.TRUE.equals(updated)) {
//            return ResponseEntity.ok(true);
//        }
//        return ResponseEntity.badRequest().body(false);
//    }

    // PATCH: partial update (only the fields sent in the body are changed)
    @PatchMapping("/update/{userId}")
    public ResponseEntity<?> patchUser(@PathVariable("") Integer userId, @RequestBody UserModel userModel) {
        try {
            UserModel updated = userService.patchUser(userId, userModel);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            logger.error("Patch failed for user {}: {}", userId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}