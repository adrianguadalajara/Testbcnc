package com.example.Testbcnc.adapter.out;

import com.example.Testbcnc.domain.Precio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface PrecioRepository extends JpaRepository<Precio, Long> {
    @Query("SELECT p FROM Precio p WHERE p.brandId = :brandId AND p.productId = :productId AND :applicationDate BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC")
    List<Precio> obtenerPrecio(@Param("brandId") Integer brandId, @Param("productId") Integer productId, @Param("applicationDate") LocalDateTime applicationDate);
}
