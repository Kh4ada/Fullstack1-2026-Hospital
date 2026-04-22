package com.hospitalVM.atenciones.controllers;

import com.hospitalVM.atenciones.models.Atencion;
import com.hospitalVM.atenciones.services.AtencionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atenciones")
@Validated
public class AtencionController {

    @Autowired
    private AtencionService atencionService;


    @GetMapping
    public ResponseEntity<List<Atencion>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.atencionService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Atencion> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.atencionService.findById(id));
    }


    @PostMapping
    public ResponseEntity<Atencion> save(@Valid @RequestBody Atencion atencion) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.atencionService.save(atencion));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Atencion> updateById(@PathVariable Long id, @Valid @RequestBody Atencion atencion) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.atencionService.updateById(id, atencion));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.atencionService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Atencion>> findByPacienteId(@PathVariable Long pacienteId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.atencionService.findByPacienteId(pacienteId));
    }


    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Atencion>> findByMedicoId(@PathVariable Long medicoId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.atencionService.findByMedicoId(medicoId));
    }
}
