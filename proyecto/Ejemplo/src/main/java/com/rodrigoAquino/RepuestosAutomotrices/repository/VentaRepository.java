package com.rodrigoAquino.RepuestosAutomotrices.repository;

import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Optional<Venta> findByIdEmpleadoAndIdRepuesto(Integer idEmpleado, Integer idRepuesto);
}
