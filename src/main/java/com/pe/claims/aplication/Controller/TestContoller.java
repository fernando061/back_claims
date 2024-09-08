package com.pe.claims.aplication.Controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestContoller {

    @GetMapping("${route.test}")
    public ResponseEntity<String> famousQuotes() {

        return ResponseEntity.ok("response");
    }
}
