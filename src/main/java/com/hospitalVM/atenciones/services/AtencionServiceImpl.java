package com.hospitalVM.atenciones.services;

import com.hospitalVM.atenciones.exceptions.AtencionException;
import com.hospitalVM.atenciones.exceptions.PacienteExistenteException;
import com.hospitalVM.atenciones.exceptions.PacienteInexistenteException;
import com.hospitalVM.atenciones.models.Atencion;
import com.hospitalVM.atenciones.models.Paciente;
import com.hospitalVM.atenciones.repositories.AtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Atencion> findAll() {
        return this.atencionRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Atencion findById(Long id) {
        return this.atencionRepository.findById(id).orElseThrow(
                () -> new AtencionException("Atencion con id: " + id + " no encontrado")
        );
    }

    @Transactional
    @Override
    public Atencion save(Atencion atencion) {
        if (this.atencionRepository.findById(atencion.getAtencionId()).isPresent()) {
            throw new PacienteExistenteException("Paciente con run: " + atencion.getAtencionId() + " ya existente");
        }
        return this.atencionRepository.save(atencion);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.atencionRepository.deleteById(id);
    }

}
