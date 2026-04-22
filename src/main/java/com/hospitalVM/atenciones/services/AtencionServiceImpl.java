package com.hospitalVM.atenciones.services;

import com.hospitalVM.atenciones.exceptions.AtencionException;
import com.hospitalVM.atenciones.models.Atencion;
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
                () -> new AtencionException("Atencion con id: " + id + " no encontrada")
        );
    }

    @Transactional
    @Override
    public Atencion save(Atencion atencion) {
        return this.atencionRepository.save(atencion);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.atencionRepository.findById(id).orElseThrow(
                () -> new AtencionException("Atencion con id: " + id + " no encontrada")
        );
        this.atencionRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Atencion updateById(Long id, Atencion atencion) {
        return this.atencionRepository.findById(id).map(element -> {
            element.setHoraAtencion(atencion.getHoraAtencion());
            element.setCosto(atencion.getCosto());
            element.setComentario(atencion.getComentario());
            element.setPaciente(atencion.getPaciente());
            element.setMedico(atencion.getMedico());
            return this.atencionRepository.save(element);
        }).orElseThrow(
                () -> new AtencionException("Atencion con id: " + id + " no encontrada")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<Atencion> findByPacienteId(Long pacienteId) {
        return this.atencionRepository.findByPacientePacienteId(pacienteId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Atencion> findByMedicoId(Long medicoId) {
        return this.atencionRepository.findByMedicoMedicoId(medicoId);
    }
}
