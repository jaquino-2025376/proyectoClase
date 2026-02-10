package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Proveedor;
import com.rodrigoAquino.RepuestosAutomotrices.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImplements implements ProveedorService {
    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImplements(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor getProveedorById(Integer Id) {
        return proveedorRepository.findById(Id).orElse(null);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedorDetalles) {
        Proveedor proveedorExistente = proveedorRepository.findById(id).orElse(null);
        if (proveedorExistente != null) {
            proveedorExistente.setNombreProveedor(proveedorDetalles.getNombreProveedor());
            proveedorExistente.setTelefonoProveedor(proveedorDetalles.getTelefonoProveedor());
            proveedorExistente.setDireccion(proveedorDetalles.getDireccion());
            proveedorExistente.setEmailProveedor(proveedorDetalles.getEmailProveedor());
            return proveedorRepository.save(proveedorExistente);
        }
        return null;
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
