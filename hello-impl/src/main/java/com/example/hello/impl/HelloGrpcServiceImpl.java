package com.example.hello.impl;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import example.myapp.helloworld.grpc.AbstractGreeterServiceRouter;
import example.myapp.helloworld.grpc.HelloReply;
import example.myapp.helloworld.grpc.HelloRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;

/**
 * HelloGrpcServiceImpl is a singleton class to build the reply using Grpc.
 */
@Singleton
public final class HelloGrpcServiceImpl extends AbstractGreeterServiceRouter {
    private static final Logger LOGGER = Logger.getLogger(HelloGrpcServiceImpl.class.getName());

    /**
     * Parametrized constructor to initialize class parameters using Google Guice.
     *
     * @param sys actor of class Actor system.
     * @param mat materializer to set medium.
     */
    @Inject
    public HelloGrpcServiceImpl(final ActorSystem sys, final Materializer mat) {
        super(mat, sys);
    }

    @Override
    public CompletionStage<HelloReply> sayHello(final HelloRequest in) {
        LOGGER.info("Greeting user" + in.getName());
        final HelloReply reply = HelloReply
                .newBuilder()
                .setMessage("Hi " + in.getName() + " (gRPC)")
                .build();
        return CompletableFuture.completedFuture(reply);
    }
}
