package com.example.hello.impl;

import akka.NotUsed;
import com.example.hello.api.HelloService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

/**
 * CareCirclesServiceImpl is service implementation of interface {@link HelloService} for providing implementation of
 * Services Call methods related to the routes for API.
 */
public class HelloServiceImpl implements HelloService {

    /**
     * Default Constructor to intialize parameters.
     */
    @Inject
    public HelloServiceImpl() {

    }

    /**
     * Gets greeting message via http.
     *
     * @param id of the user.
     * @return Greeting message.
     */
    @Override
    public ServiceCall<NotUsed, String> hello(String id) {
        return req -> CompletableFuture.completedFuture("Hi " + id + "!");
    }
}
