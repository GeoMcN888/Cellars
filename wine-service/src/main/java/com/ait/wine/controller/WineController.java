package com.ait.wine.controller;

import com.ait.wine.model.Wine;
import com.ait.wine.repository.WineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WineController {

    private Logger LOGGER = LoggerFactory.getLogger(WineController.class);

    @Autowired
    WineRepository wineRepository;

    @GetMapping("/wine")
    public List<Wine> getSuppliers(@RequestParam("country") Optional<String> country, @RequestParam("grapes")Optional<String> grapes, @RequestParam("name")Optional<String> name) {
        if (country.isPresent() && grapes.isPresent()) {
            return wineRepository.findByCountryAndGrapes(country.get(), grapes.get());
        } else if (name.isPresent()) {
            return wineRepository.findByName(name.get());
        } else if (country.isPresent()) {
            return wineRepository.findByCountry(country.get());
        }
        return wineRepository.findAll();
    }

    @GetMapping("/wine/{wineId}")
    public ResponseEntity<Wine> getWineById(@PathVariable long wineId) throws Exception {
        Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new Exception("Not found"));
        return ResponseEntity.ok(wine);
    }

    //addWine
    @PostMapping("/wine")
    public ResponseEntity addSupplier(@RequestBody Wine wine) {
        wineRepository.save(wine);
        return ResponseEntity.accepted().body(wine);
    }

    //deleteWine
    @DeleteMapping("/wine/{wineId}")
    public ResponseEntity deleteSupplierById(@PathVariable long wineId) throws Exception {
        Wine wine = wineRepository.findById(wineId).orElseThrow(() -> new Exception("Wine not found"));
        wineRepository.delete(wine);
        return ResponseEntity.noContent().build();
    }


}