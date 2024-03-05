package com.example.ClientContactMicroserviceSecurityJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientContactMicroserviceSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientContactMicroserviceSecurityJwtApplication.class, args);
        System.out.println(SpringVersion.getVersion());
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

}
