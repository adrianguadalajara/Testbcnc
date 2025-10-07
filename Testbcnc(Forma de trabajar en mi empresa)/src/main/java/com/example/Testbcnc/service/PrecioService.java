package com.example.Testbcnc.service;

import org.springframework.stereotype.Service;
import com.example.Testbcnc.dto.PrecioDTO;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface PrecioService {

    /*
     * Obtiene el precio aplicable basado en brandId, productId y applicationDate.
     */
    public Optional<PrecioDTO> obtenerPrecio(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
