package com.example.study.admin;

import com.example.study.dto.UserDto;
import com.example.study.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/user")
    ResponseEntity<UserDto> create(@RequestParam String name, @RequestParam String password) {
        adminService.create(name, password);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user")
    ResponseEntity<UserDto> update(@RequestParam String name, @RequestParam String password) {
        adminService.update(name, password);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/{name}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> get(@PathVariable("name") String name) {
        User user = adminService.get(name);
        return ResponseEntity.ok().body(new UserDto(user.getName(), user.getPassword(), user.getAuthority().name()));
    }
}
