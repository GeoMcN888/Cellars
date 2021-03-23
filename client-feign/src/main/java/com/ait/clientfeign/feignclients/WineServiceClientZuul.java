package com.ait.clientfeign.feignclients;

import com.ait.clientfeign.model.Wine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("zuul-api-gateway")
public interface WineServiceClientZuul
{
    @GetMapping("wine-service/wine/{wineId}")
    Wine getWineById(@PathVariable int wineId);

    @DeleteMapping("wine-service/wine/{wineId}")
    void deleteWineById(@PathVariable int wineId);
}


