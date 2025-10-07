package com.example.Testbcnc.port;

import com.example.Testbcnc.domain.Precio;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PrecioServicePort {
    /**
     * Obtiene el precio aplicable basado en brandId, productId y applicationDate.
     * @param brandId
     * @param productId
     * @param applicationDate
     * @return
     */
    Optional<Precio> obtenerPrecio(Integer brandId, Integer productId, LocalDateTime applicationDate);
}
