package com.example.Testbcnc.adapter.in;

import com.example.Testbcnc.domain.Precio;
import com.example.Testbcnc.port.PrecioServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/precios")
public class PrecioRestController {
    @Autowired
    private PrecioServicePort precioService;

    @GetMapping("/ping")
    public String ping() {
        return "¡PONG!";
    }
    @GetMapping("/obtenerPrecio")
    public ResponseEntity<?> obtenerPrecio(
            @RequestParam Integer brandId,
            @RequestParam Integer productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        try {
            Optional<Precio> result = precioService.obtenerPrecio(brandId, productId, applicationDate);
            // Si no se encuentra ningún precio, devolvemos un mensaje adecuado
            if(result.get().getBrandId() == null || result.get().getProductId() == null) {
                return ResponseEntity.ok("No se ha encontrado un precio para los parámetros indicados");
            }
            // Devolvemos el precio encontrado
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
        }
    }
    
}
