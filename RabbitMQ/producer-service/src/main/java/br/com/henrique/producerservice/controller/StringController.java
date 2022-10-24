package br.com.henrique.producerservice.controller;

import br.com.henrique.producerservice.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produces")
public class StringController {

    @Autowired
    private StringService stringService;

    @GetMapping
    public ResponseEntity<String> produces(@RequestParam String message) {
        stringService.produce(message);
        return ResponseEntity.ok().body("Sending message");
    }

}
