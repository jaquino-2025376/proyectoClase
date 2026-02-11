package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import com.rodrigoAquino.RepuestosAutomotrices.repository.VentaRepository;

import java.util.List;

public class VentaServiceImplements implements VentaService{
    private final VentaRepository ventaRepository;

    public VentaServiceImplements(VentaRepository ventaRepository){
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta saveVenta(Venta venta) throws RuntimeException {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Integer id, Venta ventaDetalles){
        Venta ventaExistente = ventaRepository.findById(id).orElse(null);
        if (ventaExistente != null){
            ventaExistente.setFechaVenta(ventaDetalles.getFechaVenta());
            ventaExistente.setCantidad(ventaDetalles.getCantidad());
            ventaExistente.setTotal(ventaDetalles.getTotal());
            ventaExistente.setIdEmpleado(ventaDetalles.getIdEmpleado());
            ventaExistente.setIdRepuesto(ventaDetalles.getIdRepuesto());
        }
        return null;
    }

    @Override
    public void deleteVenta(Integer id) {
    ventaRepository.deleteById(id);
    }
}
