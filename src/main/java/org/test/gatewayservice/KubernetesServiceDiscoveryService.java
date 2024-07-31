package org.test.gatewayservice;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class KubernetesServiceDiscoveryService   {

    private final KubernetesClient kubernetesClient;

    public KubernetesServiceDiscoveryService() {
        this.kubernetesClient = new KubernetesClientBuilder().build();
    }

    public List<String> getServiceUrls(String namespace, String labelSelector) {
        List<Service> services = kubernetesClient.services()
                .inNamespace(namespace)
                .withLabelSelector(labelSelector)
                .list()
                .getItems();

        return services.stream()
                .map(service -> service.getSpec().getClusterIP())
                .collect(Collectors.toList());
    }
    public List<String> getServiceNames(String namespace, String labelSelector) {
        List<Service> services = kubernetesClient.services()
                .inNamespace(namespace)
                .withLabelSelector(labelSelector)
                .list()
                .getItems();

        return services.stream()
                .map(service -> service.getMetadata().getName())
                .collect(Collectors.toList());
    }
}
