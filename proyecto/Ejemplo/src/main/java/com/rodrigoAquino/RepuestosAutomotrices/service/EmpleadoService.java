package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmpleadoService {
    List<Empleado> getAllEmpleados();
    Empleado getEmpleadoById(Integer id);
    Empleado saveEmpleado (Empleado empleado) throws RuntimeException;
    Empleado updateEmpleado(Integer id, Empleado empleado);
    void deleteEmpleado(Integer id);
}
