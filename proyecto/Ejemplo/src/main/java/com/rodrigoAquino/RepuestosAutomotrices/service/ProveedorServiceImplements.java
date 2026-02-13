package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Proveedor;
import com.rodrigoAquino.RepuestosAutomotrices.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return proveedorRepository.findById(Id).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) throws RuntimeException {
        Optional<Proveedor> proveedorExistente =
                proveedorRepository.findByNombreAndEmailProveedor(proveedor.getNombreProveedor(), proveedor.getEmailProveedor());
        if(proveedorExistente.isPresent()){
            throw new IllegalArgumentException("Ya existe un proveedor con ese nombre y correo electronico");
        }
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Integer id, Proveedor proveedorDetalles) {
        Proveedor proveedorExistente = proveedorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
        boolean sinCambios =
                proveedorExistente.getNombreProveedor().equals(proveedorDetalles.getNombreProveedor()) &&
                        proveedorExistente.getEmailProveedor().equals(proveedorDetalles.getEmailProveedor()) &&
                        proveedorExistente.getTelefonoProveedor().equals(proveedorDetalles.getTelefonoProveedor()) &&
                        proveedorExistente.getDireccion().equals(proveedorDetalles.getDireccion());

        if (proveedorExistente != null) {
            throw new IllegalArgumentException("No hay cambios para actualizar");
        }
            proveedorExistente.setNombreProveedor(proveedorDetalles.getNombreProveedor());
            proveedorExistente.setTelefonoProveedor(proveedorDetalles.getTelefonoProveedor());
            proveedorExistente.setDireccion(proveedorDetalles.getDireccion());
            proveedorExistente.setEmailProveedor(proveedorDetalles.getEmailProveedor());
            return proveedorRepository.save(proveedorExistente);
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
