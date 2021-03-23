package com.ait.clientdiscovery.controllers;



import com.ait.wine.model.Supplier;
import com.ait.clientdiscovery.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ClientDiscoveryController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("client-discovery/wine/{wineId}")
    public Wine getWineFromWineService(@PathVariable long wineId)
    {
        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("wine-service");
        ServiceInstance instance = serviceInstances.get(0);


        String serviceUri = String.format("%s/wine/{wineId}", instance.getUri().toString());
        System.err.println("ServiceUri of first instance : " + serviceUri);


        ResponseEntity<Wine> wineResponseEntity = restTemplate.exchange(

                serviceUri,

                HttpMethod.GET,

                null,

                Wine.class,
                wineId);

        return wineResponseEntity.getBody();
    }

    @GetMapping("client-discovery/suppliers/{supplierId}")
    public Supplier getSupplierFromSupplierService(@PathVariable long supplierId) {

        RestTemplate restTemplate = new RestTemplate();

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("supplier-service");
        ServiceInstance instance = serviceInstances.get(0);


        String serviceUri = String.format("%s/suppliers/{supplierId}", instance.getUri().toString());
        System.err.println("ServiceUri of first instance : " + serviceUri);


        ResponseEntity<Supplier> supplierResponseEntity = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Supplier.class, supplierId);

        return supplierResponseEntity.getBody();
    }
}
