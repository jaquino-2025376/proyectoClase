package com.rodrigoAquino.RepuestosAutomotrices.repository;

import com.rodrigoAquino.RepuestosAutomotrices.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    Optional<Proveedor> findByNombreProveedorAndEmailProveedor(String nombreProveedor, String emailProveedor);
}
