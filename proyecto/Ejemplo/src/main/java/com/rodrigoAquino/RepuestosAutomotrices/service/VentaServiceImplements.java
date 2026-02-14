package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import com.rodrigoAquino.RepuestosAutomotrices.repository.VentaRepository;
import com.rodrigoAquino.RepuestosAutomotrices.repository.EmpleadoRepository;
import com.rodrigoAquino.RepuestosAutomotrices.repository.RepuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImplements implements VentaService {

    private final VentaRepository ventaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final RepuestoRepository repuestoRepository;

    public VentaServiceImplements(
            VentaRepository ventaRepository,
            EmpleadoRepository empleadoRepository,
            RepuestoRepository repuestoRepository) {

        this.ventaRepository = ventaRepository;
        this.empleadoRepository = empleadoRepository;
        this.repuestoRepository = repuestoRepository;
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVentaById(Integer id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
    }

    @Override
    public Venta saveVenta(Venta venta) {
        empleadoRepository.findById(venta.getIdEmpleado()).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        repuestoRepository.findById(venta.getIdRepuesto()).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));
        return ventaRepository.save(venta);
    }




    @Override
    public Venta updateVenta(Integer id, Venta ventaDetalles) {
        Venta ventaExistente = ventaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
        Empleado empleado = empleadoRepository.findById(ventaDetalles.getIdEmpleado()).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));

        Repuesto repuesto = repuestoRepository.findById(ventaDetalles.getIdRepuesto()).orElseThrow(() -> new IllegalArgumentException("Repuesto no encontrado"));
        ventaExistente.setFechaVenta(ventaDetalles.getFechaVenta());
        ventaExistente.setCantidad(ventaDetalles.getCantidad());
        ventaExistente.setTotal(ventaDetalles.getTotal());
        ventaExistente.setIdEmpleado(empleado.getIdEmpleado());
        ventaExistente.setIdRepuesto(repuesto.getIdRepuesto());


        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void deleteVenta(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));

        ventaRepository.delete(venta);
    }
}
