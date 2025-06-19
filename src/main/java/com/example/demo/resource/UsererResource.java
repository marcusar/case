package com.example.demo.resource;

import com.example.demo.domain.Type;
import com.example.demo.service.UsererService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsererResource {

    private final UsererService usererService;

    public UsererResource(UsererService usererService) {
        this.usererService = usererService;
    }

    @PostMapping("/user")
    public ResponseEntity<UsererResponse> createUserer(@RequestBody UsererRequest request) {
        UsererResponse userer = usererService.createUserer(request);
        return ResponseEntity.ok(userer);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UsererResponse> getUserer(@PathVariable Long id) {
        UsererResponse userer = usererService.getUserer(id);
        return ResponseEntity.ok(userer);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UsererResponse>> getUserers(@RequestParam Type type) {
        List<UsererResponse> userers = usererService.getUserers(type);
        return ResponseEntity.ok(userers);
    }
}
