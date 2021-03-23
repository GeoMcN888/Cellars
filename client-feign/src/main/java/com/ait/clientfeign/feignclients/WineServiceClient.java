package com.ait.clientfeign.feignclients;

import com.ait.clientfeign.model.Wine;
import com.ait.wine.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("wine-service")
public interface WineServiceClient
{
    @GetMapping("wine/{wineId}")
    Wine getWineById(@PathVariable int wineId);

    @DeleteMapping("wine/{wineId}")
    void deleteWineById(@PathVariable int wineId);
}



