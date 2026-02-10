package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Proveedor;
import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepuestoService {
    List<Repuesto> getAllRepuestos();
    Repuesto getRepuestoById(Integer Id);
    Repuesto saveRepuesto (Repuesto repuesto) throws RuntimeException;
    Repuesto updateRepuesto(Integer id, Repuesto repuesto);
    void deleteRepuesto(Integer id);
}
