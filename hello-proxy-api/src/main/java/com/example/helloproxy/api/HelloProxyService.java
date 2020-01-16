package com.example.helloproxy.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.restCall;
import static com.lightbend.lagom.javadsl.api.Service.named;

/**
 * * The Hello Proxy service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and consume the HelloProxyService.
 */
public interface HelloProxyService extends Service {

    /**
     * Gets the greeting message via http call
     *
     * @param id contains the user id
     * @return the greeting message
     */
    ServiceCall<NotUsed, String> proxyViaHttp(String id);

    /**
     * Gets the greeting message via Grpc call
     *
     * @param id contains the user id
     * @return the greeting message
     */
    ServiceCall<NotUsed, String> proxyViaGrpc(String id);

    default Descriptor descriptor() {
        return named("hello-proxy")
                .withCalls(
                        restCall(Method.GET, "/proxy/rest-hello/:id", this::proxyViaHttp),
                        restCall(Method.GET, "/proxy/grpc-hello/:id", this::proxyViaGrpc)
                ).withAutoAcl(true);
    }
}

