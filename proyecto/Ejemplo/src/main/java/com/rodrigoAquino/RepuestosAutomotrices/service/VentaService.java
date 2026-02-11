package com.rodrigoAquino.RepuestosAutomotrices.service;

import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface VentaService {
    List<Venta> getAllVentas();
    Venta getVentaById(Integer id);
    Venta saveVenta(Venta venta) throws RuntimeException;
    Venta updateVenta(Integer id, Venta venta);
    void deleteVenta(Integer id)
}
