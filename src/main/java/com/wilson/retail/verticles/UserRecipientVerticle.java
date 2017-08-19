package com.wilson.retail.verticles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.wilson.retail.entity.User;
import com.wilson.retail.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRecipientVerticle extends AbstractVerticle {

    public static final String GET_ALL_USERS = "get.user.all";
    public static final String ADD_USER = "add.user";

    private final ObjectMapper mapper = Json.mapper;

    @Autowired
    private UserService userService;

    @Override
    public void start() throws Exception {
        super.start();
       vertx.eventBus()
                .<String>consumer(GET_ALL_USERS)
                .handler(getAllUserService(userService));


        vertx.eventBus().consumer(ADD_USER,addUserService(userService));
    }

    private Handler<Message<String>> getAllUserService(UserService service) {
        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                future.complete(mapper.writeValueAsString(service.getAllUsers()));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }

    private Handler<Message<JsonObject>> addUserService(UserService service) {


        return msg -> vertx.<String>executeBlocking(future -> {
            try {
                JsonObject jsonObject=msg.body();
                User user=mapper.convertValue(jsonObject,User.class);
                future.complete(mapper.writeValueAsString(service.addUser(user)));
            } catch (JsonProcessingException e) {
                System.out.println("Failed to serialize result");
                future.fail(e);
            }
        }, result -> {
            if (result.succeeded()) {
                msg.reply(result.result());
            } else {
                msg.reply(result.cause()
                        .toString());
            }
        });
    }
}
