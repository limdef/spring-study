package com.example.study.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/permit-all")
    String permitAll() {
        return "Hello Everyone";
    }

    @GetMapping("/auth")
    String auth() {
        return "Hello User";
    }
}
