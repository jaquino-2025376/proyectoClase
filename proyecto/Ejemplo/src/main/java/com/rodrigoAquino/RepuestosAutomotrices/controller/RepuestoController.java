package com.rodrigoAquino.RepuestosAutomotrices.controller;

import com.rodrigoAquino.RepuestosAutomotrices.model.Repuesto;
import com.rodrigoAquino.RepuestosAutomotrices.service.RepuestoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")

public class RepuestoController {
    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    @GetMapping
    public List<Repuesto> getAllRepuestos() {
        return repuestoService.getAllRepuestos();
    }

    @PostMapping
    public ResponseEntity<Object> createRepuesto(@Valid @RequestBody Repuesto repuesto) {
        try {
            if (repuesto.getNombreRepuesto() == null || repuesto.getNombreRepuesto().isEmpty()) {
                return ResponseEntity.badRequest().body("El nombre del repuesto no puede estar vacío");
            }
            if (repuesto.getPrecioCompra() == null || repuesto.getPrecioCompra() <= 0) {
                return ResponseEntity.badRequest().body("El precio debe ser mayor a 0");
            }
            if (repuesto.getPrecioVenta() == null || repuesto.getPrecioVenta() < 0) {
                return ResponseEntity.badRequest().body("El precio de la venta debe ser mayor a 0");
            }
            if (repuesto.getCategoriaRepuesto() == null || repuesto.getCategoriaRepuesto().isEmpty()){
                return ResponseEntity.badRequest().body("El repuesto debe tener una categoría");
            }
            Repuesto createdRepuesto = repuestoService.saveRepuesto(repuesto);
            return new ResponseEntity<>(createdRepuesto, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepuesto(@PathVariable Integer id, @Valid @RequestBody Repuesto repuesto) {
        try {
            Repuesto updateRepuesto = repuestoService.updateRepuesto(id, repuesto);
            return ResponseEntity.ok(updateRepuesto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepuesto(@PathVariable Integer id) {
        try {
            repuestoService.deleteRepuesto(id);
            return ResponseEntity.ok("El repuesto fue eliminado con éxito: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar el repuesto: " + e.getMessage());
        }
    }
}
