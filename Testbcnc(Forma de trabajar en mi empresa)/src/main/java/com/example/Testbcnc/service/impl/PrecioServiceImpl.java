package com.example.Testbcnc.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Testbcnc.dto.PrecioDTO;
import com.example.Testbcnc.mapper.PrecioMapper;
import com.example.Testbcnc.service.PrecioService;



@Service
public class PrecioServiceImpl implements PrecioService {

	
	 @Autowired
    private PrecioMapper precioMapper;

	@Override
    public Optional<PrecioDTO> obtenerPrecio(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        List<PrecioDTO> precios = precioMapper.obtenerPrecio(brandId, productId, applicationDate);
        /*System.out.println("[TRAZA] Resultados de la consulta:");
        for (PrecioDTO precio : precios) {
            System.out.println(precio.getProductId() + " " + precio.getBrandId() + " " + precio.getPrice() + " " + precio.getStartDate() + " " + precio.getEndDate());
        }
        */
        return precios.stream().findFirst();
    }


}
