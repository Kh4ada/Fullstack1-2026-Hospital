package com.hospitalVM.atenciones.services;

import com.hospitalVM.atenciones.exceptions.MedicoExistenteException;
import com.hospitalVM.atenciones.exceptions.MedicoInexistenteException;
import com.hospitalVM.atenciones.models.Medico;
import com.hospitalVM.atenciones.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Medico> findAll() {
        return this.medicoRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Medico findById(Long id) {
        return this.medicoRepository.findById(id).orElseThrow(
                () -> new MedicoInexistenteException("Medico con id: "+id+" no encontrado")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Medico findByRun(String run) {
        return this.medicoRepository.findByRun(run).orElseThrow(
                () -> new MedicoInexistenteException("Medico con rut"+run+" no encontrado")
        );
    }

    @Transactional
    @Override
    public Medico save(Medico medico) {
        if (this.medicoRepository.findByRun(medico.getRun()).isPresent()){
            throw new MedicoExistenteException("Medico con rut: "+medico.getRun()+" ya existente");
        }
        return this.medicoRepository.save(medico);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.medicoRepository.deleteById(id);
    }

    @Override
    public Medico updateById(Long id, Medico medico) {
        return this.medicoRepository.findById(id).map(element -> {
            // element.setRun(medico.getRun());
            element.setJefeTurno(medico.getJefeTurno());
            element.setNombreCompleto(medico.getNombreCompleto());
            return this.medicoRepository.save(element);

        }).orElseThrow(
                () -> new MedicoInexistenteException("El medico con id: "+id+" no existe")
        );
    }
}
