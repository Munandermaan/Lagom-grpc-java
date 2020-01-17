package com.example.hello.impl;

import akka.NotUsed;
import com.example.hello.api.HelloService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * HellosServiceImpl is service implementation of interface {@link HelloService} for providing implementation of
 * Services Call methods related to the routes for API.
 */
public final class HelloServiceImpl implements HelloService {

    private final static Logger LOGGER = Logger.getLogger(HelloServiceImpl.class.getName());

    /**
     * Default Constructor to intialize parameters.
     */
    @Inject
    public HelloServiceImpl() {
    }

    @Override
    public ServiceCall<NotUsed, String> hello(final String id) {
       LOGGER.info("Hi greeting message to user"+ LOGGER.getName());
        return req -> CompletableFuture.completedFuture("Hi " + id + "!");
    }
}
