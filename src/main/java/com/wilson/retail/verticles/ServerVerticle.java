package com.wilson.retail.verticles;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Component
public class ServerVerticle extends AbstractVerticle {





    private void getAllUsersHandler(RoutingContext routingContext) {

        vertx.eventBus()
                .<String>send(UserRecipientVerticle.GET_ALL_USERS, "", result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }

    private void addUsersHandler(RoutingContext routingContext) {
        vertx.eventBus()
                .<String>send(UserRecipientVerticle.ADD_USER,routingContext.getBodyAsJson(), result -> {
                    if (result.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "application/json")
                                .setStatusCode(200)
                                .end(result.result()
                                        .body());
                    } else {
                        routingContext.response()
                                .setStatusCode(500)
                                .end();
                    }
                });
    }

    @Override
    public void start() throws Exception {
        super.start();

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());


        router.get("/api/users")
                .handler(this::getAllUsersHandler);

        router.post("/api/users")
                .handler(this::addUsersHandler);


        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(config().getInteger("http.port", 8080));
    }

}
