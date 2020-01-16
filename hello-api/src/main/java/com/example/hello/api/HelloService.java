package com.example.hello.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;
import com.lightbend.lagom.javadsl.api.ServiceCall;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the HelloService.
 */
public interface HelloService extends Service {

    ServiceCall<NotUsed,String> hello(String id);

    default Descriptor descriptor() {
        return Service.named("hello")
            .withCalls(
                pathCall("/api/hello/:id", this::hello )
            )
            .withAutoAcl(true);
    }
}
