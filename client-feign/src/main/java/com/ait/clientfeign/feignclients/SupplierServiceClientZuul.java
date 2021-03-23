package com.ait.clientfeign.feignclients;

import com.ait.clientfeign.model.Wine;
import com.ait.wine.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("zuul-service-api-gateway")
public interface SupplierServiceClientZuul
{
    @GetMapping("supplier-service/suppliers/{supplierId}")
    Supplier getSupplierById(@PathVariable long supplierId);

    @DeleteMapping("supplier-service/suppliers/{supplierId}")
    void deleteSupplierById(@PathVariable long supplierId);

    @PostMapping("supplier-service/suppliers/{supplierId}")
    Supplier addSupplier(@RequestBody Supplier supplier) ;

    @GetMapping("supplier-service/suppliers")
    public List<Supplier> getSuppliers(@RequestParam("name") Optional<String> name, @RequestParam("country")Optional<String> country);

}
