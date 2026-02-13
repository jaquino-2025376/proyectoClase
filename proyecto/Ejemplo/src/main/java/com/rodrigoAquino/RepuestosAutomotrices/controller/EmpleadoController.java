    package com.rodrigoAquino.RepuestosAutomotrices.controller;

    import com.rodrigoAquino.RepuestosAutomotrices.model.Empleado;
    import com.rodrigoAquino.RepuestosAutomotrices.service.EmpleadoService;
    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/empleados")

    public class EmpleadoController {
        private final EmpleadoService empleadoService;

        public EmpleadoController(EmpleadoService empleadoService) {
            this.empleadoService = empleadoService;
        }

        @GetMapping
        public List<Empleado> getAllEmpleados() {
            return empleadoService.getAllEmpleados();
        }

        @PostMapping
        public ResponseEntity<Object> createEmpleado(@Valid @RequestBody Empleado empleado) {
            try {
                if (empleado.getNombreEmpleado() == null || empleado.getNombreEmpleado().isEmpty()) {
                    return ResponseEntity.badRequest().body("El nombre no puede estar vacío");
                }
                if (empleado.getApellidoEmpleado() == null || empleado.getApellidoEmpleado().isEmpty()){
                    return ResponseEntity.badRequest().body("El apellido no puede estar vacío");
                }
                if(empleado.getPuestoEmpleado() == null || empleado.getPuestoEmpleado().isEmpty()){
                    return ResponseEntity.badRequest().body("El empleado debe de tener un puesto");
                }
                Empleado createdEmpleado = empleadoService.saveEmpleado(empleado);
                return new ResponseEntity<>(createdEmpleado, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }


        @PutMapping("/{id}")
        public ResponseEntity<Object> updateEmpleado (@PathVariable Integer id, @Valid @RequestBody Empleado empleado){
            try {
                Empleado updateEmpleado = empleadoService.updateEmpleado(id, empleado);
                return ResponseEntity.ok(updateEmpleado);
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object>deleteEmpleado (@PathVariable Integer id){
            try {
                empleadoService.deleteEmpleado(id);
                return  ResponseEntity.ok("EL empleado fue eliminado con exito " + id);
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar: " + e.getMessage());
            }
        }

    }