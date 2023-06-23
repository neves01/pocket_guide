package br.com.henrique.springbootlocalstack.controller;

import br.com.henrique.springbootlocalstack.configuration.ParameterStoreConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ParameterStoreController {

    @Autowired
    private ParameterStoreConfiguration parameterStoreConfiguration;

    @GetMapping("/parameterStoreConfiguration")
    public String configuration() {
        return parameterStoreConfiguration.getHelloWorld();
    }
}
