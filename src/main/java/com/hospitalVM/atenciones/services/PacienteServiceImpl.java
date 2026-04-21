package com.hospitalVM.atenciones.services;

import com.hospitalVM.atenciones.exceptions.PacienteExistenteException;
import com.hospitalVM.atenciones.exceptions.PacienteInexistenteException;
import com.hospitalVM.atenciones.models.Paciente;
import com.hospitalVM.atenciones.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findAll() {
        return this.pacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findById(Long id) {
        return this.pacienteRepository.findById(id).orElseThrow(
                () -> new PacienteInexistenteException("Paciente con id: " + id + " no encontrado")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findByRun(String run) {
        return this.pacienteRepository.findByRun(run).orElseThrow(
                () -> new PacienteInexistenteException("Paciente con run: " + run + " no encontrado")
        );
    }

    @Transactional
    @Override
    public Paciente save(Paciente paciente) {
        if (this.pacienteRepository.findByRun(paciente.getRun()).isPresent()) {
            throw new PacienteExistenteException("Paciente con run: " + paciente.getRun() + " ya existente");
        }
        return this.pacienteRepository.save(paciente);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.pacienteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Paciente updateById(Long id, Paciente paciente) {
        return this.pacienteRepository.findById(id).map(element -> {
            element.setNombres(paciente.getNombres());
            element.setApellidos(paciente.getApellidos());
            element.setFechaNacimiento(paciente.getFechaNacimiento());
            element.setCorreo(paciente.getCorreo());
            // El run no se actualiza (igual que Medico no actualiza run)
            return this.pacienteRepository.save(element);
        }).orElseThrow(
                () -> new PacienteInexistenteException("El paciente con id: " + id + " no existe")
        );
    }
}
