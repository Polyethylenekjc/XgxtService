package com.api.xgxt.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.xgxt.entity.Password;
import com.api.xgxt.entity.UserEntity;
import com.api.xgxt.service.UserService;

@RestController	
@RequestMapping(path="/api")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(path="/createAdmin")
    public void createAdmin(@RequestBody Password password) {
        userService.createAdmin(password);
    }

    @GetMapping(path="/getAllUsers")
    public ResponseEntity<String> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path="/addNewUser")
    public ResponseEntity<String> addNewUser (@RequestBody List<UserEntity> user) {
        return userService.addNewUser(user);
    }

    @GetMapping("/findById")
    public ResponseEntity<String> findBEntity(@RequestParam Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping("/checkPassword")
    public ResponseEntity<String> checkPassword(@RequestBody UserEntity user) {
        return userService.checkPassword(user.getId(), user.getPassword());
    }
    
    @GetMapping("/checkPermission") 
    public ResponseEntity<String> checkPermission(@RequestParam Long id) {
        return userService.CheckPermission(id);
    }
    
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {
        return userService.updateUser(user);
    }

    @PutMapping("/updateUserId")
    public ResponseEntity<String>  updateUserId(@RequestParam Long id, Long newid) {
        return userService.updateUserId(id, newid);
    }
    
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUserPassword")
    public ResponseEntity<String> updateUserPassword(@RequestBody Password newpassword) {
        return userService.updateUserPassword(newpassword);
    }

    @PutMapping("/sudoUser")
    public ResponseEntity<String> sudoUser(@RequestBody List<Password> input) {
        return userService.sudoUser(input.get(0), input.get(1));
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<String> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/getUserByClasses")
    public ResponseEntity<String> getUserByClasses(@RequestParam String classes) {
        return userService.getUserByClasses(classes);
    }

    @GetMapping("/getUserByGrade")
    public ResponseEntity<String> getUserByGrade(@RequestParam String grade) {
        return userService.getUserByGrade(grade);
    }

    @GetMapping("/getUserByMajor")
    public ResponseEntity<String> getUserByMajor(@RequestParam String major) {
        return userService.getUserByMajor(major);
    }    

    @GetMapping("/getUserBySubject")
    public ResponseEntity<String> getUserBySubject(@RequestParam String subject) {
        return userService.getUserBySubject(subject);
    }   

    @GetMapping("/getUserByGradeAndMajor")
    public ResponseEntity<String> getUserByGradeAndMajor(@RequestParam String grade, String major) {
        return userService.getUserByGradeAndMajor(grade, major);
    }   

    @GetMapping("/getUserByMajorAndSubject")
    public ResponseEntity<String> getUserByMajorAndSubject(@RequestParam String major, String subject) {
        return userService.getUserByMajorAndSubject(major, subject);
    }   

    @GetMapping("/getUserByGradeAndSubject")        
    public ResponseEntity<String> getUserByGradeAndSubject(@RequestParam String grade, String subject) {
        return userService.getUserByGradeAndSubject(grade, subject);
    }
}
