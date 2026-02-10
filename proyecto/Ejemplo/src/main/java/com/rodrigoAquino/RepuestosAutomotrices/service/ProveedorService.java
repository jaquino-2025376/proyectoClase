package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Proveedor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProveedorService {
    List<Proveedor> getAllEmpleados();
    Proveedor getProveedorById(Integer Id);
    Proveedor saveProveedor (Proveedor proveedor) throws RuntimeException;
    Proveedor updateProveedor(Integer id, Proveedor proveedor);
    void deleteProveedor(Integer id);
}
