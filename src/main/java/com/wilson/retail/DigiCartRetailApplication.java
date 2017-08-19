package com.wilson.retail;

import javax.annotation.PostConstruct;

import com.wilson.retail.verticles.ServerVerticle;
import com.wilson.retail.verticles.UserRecipientVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.vertx.core.Vertx;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("com.wilson.retail.repository")
@EntityScan("com.wilson.retail.entity")
@ComponentScan(basePackages = { "com.wilson" })
public class DigiCartRetailApplication {

    @Autowired
    private ServerVerticle serverVerticle;


    @Autowired
    private UserRecipientVerticle userServiceVerticle;

    public static void main(String[] args) {
        SpringApplication.run(DigiCartRetailApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        final Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(serverVerticle);
        vertx.deployVerticle(userServiceVerticle);


    }
}
