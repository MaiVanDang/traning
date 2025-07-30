package com.practice.controller;

import com.practice.model.UserForm;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AnnotationDemoController {
    // 1. @RequestParam
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Xin chào " + name + "!";
    }

    // 2. @PathVariable
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + " from @PathVariable!";
    }

    // 3. @RequestBody
    @PostMapping("/json")
    public String jsonExample(@RequestBody UserForm user) {
        return "Received JSON: " + user.getUsername() + " - " + user.getEmail();
    }

    // 4. @ModelAttribute (form-url-encoded)
    @PostMapping("/form")
    public String formExample(@ModelAttribute UserForm user) {
        return "Form submit: " + user.getUsername() + " - " + user.getEmail();
    }
}
