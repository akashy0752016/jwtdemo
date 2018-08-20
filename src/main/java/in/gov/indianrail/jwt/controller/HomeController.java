package in.gov.indianrail.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest/hello")
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String hello() {
        return "hello world";
    }
}
