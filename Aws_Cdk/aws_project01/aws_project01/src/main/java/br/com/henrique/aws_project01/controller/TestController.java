package br.com.henrique.aws_project01.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/dog/{name}")
    public ResponseEntity<?> dogTest(@PathVariable String name) {
        LOG.info("Test controller - name: {}", name);

        return ResponseEntity.ok("Name : " + name);
    }

    @GetMapping("/dog2/{name}")
    public ResponseEntity<?> dogTest2(@PathVariable String name) {
        LOG.info("Test controller - name: {}", name);

        return ResponseEntity.ok("Name2 : " + name);
    }


}
