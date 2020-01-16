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

/**
 * HelloGrpcServiceImpl is a singleton class to build the reply using Http.
 */
@Singleton
public class HelloProxyServiceImpl implements HelloProxyService {

    private HelloService helloService;
    private GreeterServiceClient greeterClient;

    /**
     * Parametrized constructor to initialize class parameters using Google Guice.
     *
     * @param helloService  handle HelloService.
     * @param greeterClient handle GreeterClient.
     */
    @Inject
    public HelloProxyServiceImpl(
            HelloService helloService,
            GreeterServiceClient greeterClient) {
        this.helloService = helloService;
        this.greeterClient = greeterClient;
    }

    /**
     * Calls the hello service.
     *
     * @param id contains the user id .
     * @return Greeting message via http.
     */
    @Override
    public ServiceCall<NotUsed, String> proxyViaHttp(String id) {
        return req -> helloService.hello(id).invoke();
    }


    /**
     * Calls the hello service.
     *
     * @param id contains the user id .
     * @return Greeting message via Grpc.
     */
    @Override
    public ServiceCall<NotUsed, String> proxyViaGrpc(String id) {
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
