package org.test.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
public class GatewayConfiguration {

    @Autowired
    private KubernetesServiceDiscoveryService kubernetesServiceDiscovery;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        List<String> serviceNames = kubernetesServiceDiscovery.getServiceNames("default", "app=myapp");

        serviceNames.forEach(serviceName -> {
            routes.route(serviceName, r -> r
                    .path("/" + serviceName + "/**")
                    .uri("lb://" + serviceName)
            );
        });
        return routes.build();
    }
}
