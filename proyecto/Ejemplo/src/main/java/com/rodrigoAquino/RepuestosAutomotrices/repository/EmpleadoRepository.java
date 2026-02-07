package com.rodrigoAquino.RepuestosAutomotrices.repository;

import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
