package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
import com.rodrigoAquino.RepuestosAutomotrices.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImplements implements EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImplements(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));}


    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        Optional<Empleado> empleadoExistente =
                empleadoRepository.findByNombreEmpleadoAndApellidoEmpleado(empleado.getNombreEmpleado(), empleado.getApellidoEmpleado());
        if (empleadoExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con ese nombre y apellido");
        }
        return empleadoRepository.save(empleado);
    }


    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleadoDetalles) {
        Empleado empleadoExistente = empleadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado"));
        boolean sinCambios =
                empleadoExistente.getNombreEmpleado().equals(empleadoDetalles.getNombreEmpleado()) &&
                        empleadoExistente.getApellidoEmpleado().equals(empleadoDetalles.getApellidoEmpleado()) &&
                        empleadoExistente.getEmailEmpleado().equals(empleadoDetalles.getEmailEmpleado()) &&
                        empleadoExistente.getPuestoEmpleado().equals(empleadoDetalles.getPuestoEmpleado());
        if (sinCambios) {
            throw new IllegalArgumentException("No hay cambios para actualizar");
        }
        empleadoExistente.setNombreEmpleado(empleadoDetalles.getNombreEmpleado());
        empleadoExistente.setApellidoEmpleado(empleadoDetalles.getApellidoEmpleado());
        empleadoExistente.setEmailEmpleado(empleadoDetalles.getEmailEmpleado());
        empleadoExistente.setPuestoEmpleado(empleadoDetalles.getPuestoEmpleado());
        return empleadoRepository.save(empleadoExistente);
    }



    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}