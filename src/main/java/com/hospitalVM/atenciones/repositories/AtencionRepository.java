package com.hospitalVM.atenciones.repositories;

import com.hospitalVM.atenciones.models.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {


    List<Atencion> findByPacientePacienteId(Long pacienteId);


    List<Atencion> findByMedicoMedicoId(Long medicoId);
}
