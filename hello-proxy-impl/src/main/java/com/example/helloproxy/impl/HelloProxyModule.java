package com.example.helloproxy.impl;

import com.example.hello.api.HelloService;
import com.example.helloproxy.api.HelloProxyService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Module to bind APIs and services.
 */
public class HelloProxyModule extends AbstractModule implements ServiceGuiceSupport {

    /**
     * Configures a Binder via the exposed methods.
     */
    @Override
    protected void configure() {
        bindClient(HelloService.class);
        bindService(HelloProxyService.class, HelloProxyServiceImpl.class);
    }

}
