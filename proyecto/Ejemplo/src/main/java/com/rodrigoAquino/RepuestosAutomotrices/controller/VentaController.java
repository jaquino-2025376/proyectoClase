package com.rodrigoAquino.RepuestosAutomotrices.controller;

import com.rodrigoAquino.RepuestosAutomotrices.model.Venta;
import com.rodrigoAquino.RepuestosAutomotrices.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")

public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService){this.ventaService = ventaService;}

    @GetMapping
    public List<Venta> getAllVentas(){return ventaService.getAllVentas();}

    @PostMapping
    public ResponseEntity<Object> createVenta(@Valid @RequestBody Venta venta){
        try {
            Venta createdVenta = ventaService.saveVenta(venta);
            return new ResponseEntity<>(createdVenta, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenta(@PathVariable Integer id, @Valid @RequestBody Venta venta){
        try{
            Venta updateVenta = ventaService.updateVenta(id, venta);
            return ResponseEntity.ok(updateVenta);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVenta(@PathVariable Integer id){
        try{
            ventaService.deleteVenta(id);
            return ResponseEntity.ok("La venta fue eliminada con éxito: " + id);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar la venta: " + e.getMessage());
        }
    }
}
