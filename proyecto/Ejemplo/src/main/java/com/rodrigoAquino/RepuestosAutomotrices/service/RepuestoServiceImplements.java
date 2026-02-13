package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import com.rodrigoAquino.RepuestosAutomotrices.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepuestoServiceImplements implements RepuestoService  {
    private final RepuestoRepository repuestoRepository;

    public RepuestoServiceImplements(RepuestoRepository repuestoRepository) {
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Repuesto> getAllRepuestos() {
        return repuestoRepository.findAll();
    }

    @Override
    public Repuesto getRepuestoById(Integer id){
        return repuestoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));
    }


    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) {
        Optional<Repuesto> repuestoExistente = repuestoRepository.findByNombreRepuesto(repuesto.getNombreRepuesto());
        if (repuestoExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un repuesto con ese nombre");
        }
        return repuestoRepository.save(repuesto);
    }


    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuestoDetalles) {
        Repuesto repuestoExistente = repuestoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));
        boolean sinCambios =
                repuestoExistente.getNombreRepuesto().equals(repuestoDetalles.getNombreRepuesto()) &&
                        repuestoExistente.getCategoriaRepuesto().equals(repuestoDetalles.getCategoriaRepuesto()) &&
                        repuestoExistente.getPrecioCompra().equals(repuestoDetalles.getPrecioCompra()) &&
                        repuestoExistente.getIdProveedor().equals(repuestoDetalles.getIdProveedor());
        if (sinCambios) {
            throw new IllegalArgumentException("No hay cambios para actualizar");
        }
        repuestoExistente.setNombreRepuesto(repuestoDetalles.getNombreRepuesto());
        repuestoExistente.setCategoriaRepuesto(repuestoDetalles.getCategoriaRepuesto());
        repuestoExistente.setPrecioCompra(repuestoDetalles.getPrecioCompra());
        repuestoExistente.setIdProveedor(repuestoDetalles.getIdProveedor());

        return repuestoRepository.save(repuestoExistente);
    }


    @Override
    public void deleteRepuesto(Integer id) {
        repuestoRepository.deleteById(id);
    }

}
