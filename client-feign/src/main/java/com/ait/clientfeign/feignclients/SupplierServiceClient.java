package com.ait.clientfeign.feignclients;

import com.ait.wine.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient("supplier-service")
public interface SupplierServiceClient {

   @GetMapping("suppliers/{supplierId}")
   Supplier getSupplierById(@PathVariable long supplierId);
   @DeleteMapping("suppliers/{supplierId}")
   void deleteSupplierById(@PathVariable long supplierId);

   @PostMapping("suppliers/{supplierId}")
   Supplier addSupplier(@RequestBody Supplier supplier) ;

   @GetMapping("/suppliers")
   public List<Supplier> getSuppliers(@RequestParam("name") Optional<String> name, @RequestParam("country")Optional<String> country);

}

