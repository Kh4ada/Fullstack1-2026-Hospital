package com.hospitalVM.atenciones.controllers;

import com.hospitalVM.atenciones.models.Paciente;
import com.hospitalVM.atenciones.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Validated
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pacienteService.findById(id));
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<Paciente> findByRun(@PathVariable String run) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pacienteService.findByRun(run));
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<Paciente> findByCorreo(@PathVariable String correo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pacienteService.findByCorreo(correo));
    }

    @PostMapping
    public ResponseEntity<Paciente> save(@Valid @RequestBody Paciente paciente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.pacienteService.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updateById(@PathVariable Long id, @Valid @RequestBody Paciente paciente) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.pacienteService.updateById(id, paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.pacienteService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
