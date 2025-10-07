
package com.example.Testbcnc.rest;

import com.example.Testbcnc.dto.PrecioDTO;
import com.example.Testbcnc.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/precios")
public class TestbcncRest {

    @Autowired
    private PrecioService precioService;

    
    @GetMapping("/saludo")
    public String saludo() {
        return "¡Hola desde el servicio REST!";
    }


    @GetMapping("/obtenerPrecio")
    public ResponseEntity<?> obtenerPrecio(
            @RequestParam Integer brandId,
            @RequestParam Integer productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

                
        return precioService.obtenerPrecio(brandId, productId, applicationDate)
            .map(precio -> {
                Map<String, Object> response = new HashMap<>();
                response.put("productId", precio.getProductId());
                response.put("brandId", precio.getBrandId());
                response.put("priceList", precio.getPriceList());
                response.put("startDate", precio.getStartDate());
                response.put("endDate", precio.getEndDate());
                response.put("price", precio.getPrice());
                return ResponseEntity.ok(response);
            })
            .orElse(ResponseEntity.notFound().build());
    }
}
