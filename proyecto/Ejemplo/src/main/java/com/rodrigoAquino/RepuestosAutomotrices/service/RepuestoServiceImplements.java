package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import com.rodrigoAquino.RepuestosAutomotrices.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Repuesto getRepuestoById(Integer Id){
        return repuestoRepository.findById(Id).orElse(null);
    }

    @Override
    public Repuesto saveRepuesto(Repuesto repuesto) throws RuntimeException{
        return repuestoRepository.save(repuesto);
    }

    @Override
    public Repuesto updateRepuesto(Integer id, Repuesto repuestoDetalles) {
        Repuesto repuestoExistente = repuestoRepository.findById(id).orElse(null);
        if (repuestoExistente != null){
            repuestoExistente.setNombreRepuesto(repuestoDetalles.getNombreRepuesto());
            repuestoExistente.setCategoriaRepuesto(repuestoDetalles.getCategoriaRepuesto());
            repuestoExistente.setPrecioCompra(repuestoDetalles.getPrecioCompra());
            repuestoExistente.setIdProveedor(repuestoDetalles.getIdProveedor());
            return repuestoRepository.save(repuestoExistente);
        }
        return null;
    }

    @Override
    public void deleteRepuesto(Integer id) {
        repuestoRepository.deleteById(id);
    }

}
