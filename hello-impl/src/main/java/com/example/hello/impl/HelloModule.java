package com.example.hello.impl;

import com.example.hello.api.HelloService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Module to bind APIs and services.
 */
public final class HelloModule extends AbstractModule implements ServiceGuiceSupport {

    /**
     * Configures a Binder via the exposed methods.
     */
    @Override
    protected void configure() {
        bindService(
            HelloService.class, HelloServiceImpl.class,
            additionalRouter(HelloGrpcServiceImpl.class)
        );
    }
}
