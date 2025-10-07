package com.example.Testbcnc.application;

import com.example.Testbcnc.domain.Precio;
import com.example.Testbcnc.port.PrecioServicePort;
import com.example.Testbcnc.adapter.out.PrecioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrecioServiceImpl implements PrecioServicePort {
    @Autowired
    private PrecioRepository precioRepository;

    @Override
    public Optional<Precio> obtenerPrecio(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        try {
            List<Precio> precios = precioRepository.obtenerPrecio(brandId, productId, applicationDate);
            if(precios.isEmpty()) {
                return Optional.of(new Precio());
            }
            return precios.stream().findFirst();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el precio: " + e.getMessage(), e);
        }
    }
}
