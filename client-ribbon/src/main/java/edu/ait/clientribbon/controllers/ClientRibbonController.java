package edu.ait.clientribbon.controllers;

import com.ait.wine.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientRibbonController
{
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("client-ribbon/suppliers/{supplierId}")
    public Supplier getSupplierFromSupplierService(@PathVariable int supplierId)
    {
        ResponseEntity<Supplier> supplierResponseEntity = restTemplate.exchange(
                "http://supplier-service/suppliers/{supplierId}",
                HttpMethod.GET,
               null,
                Supplier.class,
                supplierId);

        return supplierResponseEntity.getBody();
    }
}
