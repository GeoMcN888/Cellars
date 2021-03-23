package com.ait.clientfeign.controllers;

import com.ait.clientfeign.feignclients.SupplierServiceClient;
import com.ait.clientfeign.feignclients.SupplierServiceClientZuul;
import com.ait.clientfeign.feignclients.WineServiceClient;
import com.ait.clientfeign.feignclients.WineServiceClientZuul;
import com.ait.wine.model.Supplier;
import com.ait.clientfeign.model.Wine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientFeignController
{
    @Autowired
    WineServiceClient wineServiceClient;

    @Autowired
    WineServiceClientZuul wineServiceZuulClient;

    @GetMapping("client-feign/wine/{wineId}")
    public Wine getWineFromWineService(@PathVariable int wineId)
    {
        return wineServiceClient.getWineById(wineId);
    }

    @GetMapping("client-feign-zuul/wine/{wineId}")
    public Wine getWineFromWineServiceZuul(@PathVariable int wineId)
    {
        return wineServiceZuulClient.getWineById(wineId);
    }


    @Autowired
    SupplierServiceClient supplierServiceClient;

    @Autowired
    SupplierServiceClientZuul supplierServiceZuulClient;

    @GetMapping("client-feign/suppliers/{supplierId}")
    public Supplier getSupplierFromWineService(@PathVariable long supplierId)
    {
        return supplierServiceClient.getSupplierById(supplierId);
    }

    @GetMapping("client-feign-zuul/suppliers/{supplierId}")
    public Supplier getSupplierFromSupplierServiceZuul(@PathVariable long supplierId){
        return supplierServiceZuulClient.getSupplierById(supplierId);
    }

    @PostMapping("client-feign-zuul/suppliers/{supplierId}")
    Supplier addSupplierFromSupplierServiceZuul(@RequestBody Supplier supplier) {
        return supplierServiceZuulClient.addSupplier(supplier);
    }

    @GetMapping("client-feign-zuul/suppliers")
    public List<Supplier> getSuppliersFromSupplierServiceZuul(@RequestParam("name") Optional<String> name, @RequestParam("country")Optional<String> country){
        return supplierServiceZuulClient.getSuppliers(name, country );
    }


}
