package com.example.helloproxy.impl;

import akka.NotUsed;
import com.example.hello.api.HelloService;
import com.example.helloproxy.api.HelloProxyService;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import example.myapp.helloworld.grpc.GreeterServiceClient;
import example.myapp.helloworld.grpc.HelloReply;
import example.myapp.helloworld.grpc.HelloRequest;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.logging.Logger;

/**
 * HelloGrpcServiceImpl is a singleton class to build the reply using Http.
 */
@Singleton
public final class  HelloProxyServiceImpl implements HelloProxyService {
    private static final Logger LOGGER = Logger.getLogger(HelloProxyServiceImpl.class.getName());
    private final HelloService helloService;
    private final GreeterServiceClient greeterClient;

    /**
     * Parametrized constructor to initialize class parameters using Google Guice.
     *
     * @param helloService  handle HelloService.
     * @param greeterClient handle GreeterClient.
     */
    @Inject
    public HelloProxyServiceImpl(final HelloService helloService, final GreeterServiceClient greeterClient) {
        this.helloService = helloService;
        this.greeterClient = greeterClient;
    }

    @Override
    public ServiceCall<NotUsed, String> proxyViaHttp(final String id) {
        LOGGER.info("Greeting through http "+ LOGGER.getName());
        return req -> helloService.hello(id).invoke();
    }

    @Override
    public ServiceCall<NotUsed, String> proxyViaGrpc(final String id) {
        LOGGER.info("Greeting via GRPC"+ LOGGER.getName());
        return req -> greeterClient
                .sayHello(
                        HelloRequest
                                .newBuilder()
                                .setName(id)
                                .build()
                ).thenApply(
                        HelloReply::getMessage
                );
    }
}
