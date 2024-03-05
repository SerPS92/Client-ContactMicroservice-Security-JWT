package com.example.ClientContactMicroserviceSecurityJWT.Controller;


import com.example.ClientContactMicroserviceSecurityJWT.Model.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class PersonController {

    private final RestTemplate restTemplate;
    String url = "http://localhost:8080";
    String user = "admin";
    String pwd = "admin";
    String token;
    HttpHeaders httpHeaders = new HttpHeaders();

    @PostConstruct
    public void authenticate(){
        token = restTemplate.postForObject(url + "/login?user="+ user + "&pwd=" + pwd, null, String.class);
        httpHeaders.add("Authorization", "Bearer " + token);
        System.out.println("Token: " + token);
    }

    public PersonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/people/{name}/{email}/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> addPerson(@PathVariable(name = "name")String name,
                                  @PathVariable(name = "email")String email,
                                  @PathVariable(name = "age") int age){
        Person person = new Person(name, email, age);
        restTemplate.exchange(url + "/contacts", HttpMethod.POST, new HttpEntity<Person>(person, httpHeaders), String.class);
        Person[] people = restTemplate.exchange(
                url + "/contacts", HttpMethod.GET, new HttpEntity<>(httpHeaders), Person[].class).getBody();
        return Arrays.asList(people);
    }
}
