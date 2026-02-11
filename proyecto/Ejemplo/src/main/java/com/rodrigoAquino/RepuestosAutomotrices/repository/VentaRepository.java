package com.rodrigoAquino.RepuestosAutomotrices.repository;

import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
