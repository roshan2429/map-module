package com.example.mapmodule.controller;

import com.example.mapmodule.model.User;
import com.example.mapmodule.Service.userservice;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private userservice userService;
    @GetMapping("/within-radius")
    public ResponseEntity<List<User>>getUsersWithinRadius(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double radius) {

        List<User> users= userService.findUsersWithinRadius(lat, lon, radius);
        return  ResponseEntity.ok(users);

    }
}
